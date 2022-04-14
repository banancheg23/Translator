package com.banancheg.utils

import com.banancheg.model.data.AppState
import com.banancheg.model.data.DataModel
import com.banancheg.model.data.Meanings


fun convertMeaningsToString(meanings: List<Meanings>?) = when(meanings) {
    null -> ""
    else -> meanings.joinToString {
        it.translation?.translation.toString()
    }
}

fun parseLocalSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, false))
}

private fun mapResult(appState: AppState, isOnline: Boolean): List<DataModel> {
    val newSearchResults = arrayListOf<DataModel>()
    if (appState is AppState.Success) getSuccessResultData(appState, isOnline, newSearchResults)
    return newSearchResults
}

private fun getSuccessResultData(
    appState: AppState.Success,
    isOnline: Boolean,
    newDataModels: ArrayList<DataModel>
) {
    val dataModels: List<DataModel> = appState.data as List<DataModel>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModels.add(DataModel(searchResult.text, arrayListOf()))
            }
        }
    }
}

private fun parseOnlineResult(dataModel: DataModel, newDataModels: ArrayList<DataModel>) {
    val meanings = dataModel.meanings
    if (!dataModel.text.isNullOrBlank() && !meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in meanings) {
            val translation = meaning.translation
            if (translation != null &&
                !translation.translation.isNullOrBlank()) {
                newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModel(dataModel.text, newMeanings))
        }
    }
}