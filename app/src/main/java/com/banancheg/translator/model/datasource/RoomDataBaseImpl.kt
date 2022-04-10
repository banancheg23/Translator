package com.banancheg.translator.model.datasource

import com.banancheg.translator.db.HistoryDao
import com.banancheg.translator.model.data.AppState
import com.banancheg.translator.model.data.DataModel
import com.banancheg.translator.utils.convertDataModelSuccessToEntity
import com.banancheg.translator.utils.mapHistoryEntityToSearchResult
import io.reactivex.Observable

class RoomDataBaseImpl(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.getAllHistory())
    }

    override suspend fun saveToDb(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}