package com.banancheg.repository

import com.banancheg.model.data.AppState
import com.banancheg.model.data.DataModel
import com.banancheg.model.data.Meanings
import com.banancheg.model.data.Translation
import com.banancheg.repository.db.HistoryEntity
import com.banancheg.utils.convertMeaningsToString

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