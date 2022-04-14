package com.banancheg.repository

import com.banancheg.model.data.AppState
import com.banancheg.model.data.DataModel
import com.banancheg.repository.db.HistoryDao

class RoomDataBaseImpl(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.getDataByWord(word))
    }

    override suspend fun getAllHistory(): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.getAllHistory())
    }

    override suspend fun saveToDb(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}