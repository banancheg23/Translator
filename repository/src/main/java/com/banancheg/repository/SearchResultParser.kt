package com.banancheg.repository

import com.banancheg.model.data.AppState
import com.banancheg.model.data.SearchResultDto
import com.banancheg.model.data.MeaningsDto
import com.banancheg.model.data.TranslationDto
import com.banancheg.repository.db.HistoryEntity
import com.banancheg.utils.convertMeaningsToString

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<SearchResultDto> {
    val dataModel= ArrayList<SearchResultDto>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            dataModel.add(SearchResultDto(entity.word, listOf( MeaningsDto(TranslationDto(entity.translation), entity.imageUrl) )))
        }
    }
    return dataModel
}

fun mapHistoryEntityToSearchResult(entity: HistoryEntity?): List<SearchResultDto> {
    val dataModel= ArrayList<SearchResultDto>()
    if (entity == null) return dataModel
    dataModel.add(SearchResultDto(entity.word, listOf( MeaningsDto(TranslationDto(entity.translation), entity.imageUrl) )))
    return dataModel
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isEmpty()) {
                null
            } else {
                HistoryEntity(
                    searchResult[0].text, null,
                    convertMeaningsToString(searchResult[0].meanings),
                    searchResult[0].meanings[0].imageUrl
                )
            }
        }
        else -> null
    }
}