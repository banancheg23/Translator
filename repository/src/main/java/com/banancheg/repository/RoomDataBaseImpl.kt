package com.banancheg.repository

import com.banancheg.model.data.AppState
import com.banancheg.model.data.SearchResultDto
import com.banancheg.repository.db.HistoryDao

class RoomDataBaseImpl(private val historyDao: HistoryDao) : DataSourceLocal<List<SearchResultDto>> {
    override suspend fun getData(word: String): List<SearchResultDto> {
        return mapHistoryEntityToSearchResult(historyDao.getDataByWord(word))
    }

    override suspend fun getAllHistory(): List<SearchResultDto> {
        return mapHistoryEntityToSearchResult(historyDao.getAllHistory())
    }

    override suspend fun saveToDb(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}