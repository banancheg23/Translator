package com.banancheg.translator.utils

import com.banancheg.translator.db.HistoryEntity
import com.banancheg.translator.model.data.AppState
import com.banancheg.translator.model.data.DataModel
import com.banancheg.translator.model.data.Meanings
import com.banancheg.translator.model.data.Translation

private fun parseResult(dataModel: DataModel, newDataModels: ArrayList<DataModel>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation != null && !meaning.translation.translation.isNullOrBlank()) {
                newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModel(dataModel.text, newMeanings))
        }
    }
}

fun convertMeaningsToString(meanings: List<Meanings>?) = when(meanings) {
    null -> ""
    else -> meanings.joinToString {
        it.translation?.translation.toString()
    }
}

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModel> {
    val dataModel= ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            dataModel.add(DataModel(entity.word, listOf( Meanings(Translation(entity.translation), entity.imageUrl) )))
        }
    }
    return dataModel
}

fun mapHistoryEntityToSearchResult(entity: HistoryEntity?): List<DataModel> {
    val dataModel= ArrayList<DataModel>()
    if (entity == null) return dataModel
    dataModel.add(DataModel(entity.word, listOf( Meanings(Translation(entity.translation), entity.imageUrl) )))
    return dataModel
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text.toString(), null,
                    convertMeaningsToString(searchResult[0].meanings),
                    searchResult[0].meanings?.get(0)?.imageUrl
                )
            }
        }
        else -> null
    }
}

fun parseOnlineSearchResults(appState: AppState): AppState {
    return AppState.Success(mapResult(appState, true))
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
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation != null &&
                !meaning.translation.translation.isNullOrBlank()) {
                newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModel(dataModel.text, newMeanings))
        }
    }
}