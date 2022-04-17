package com.banancheg.repository

import com.banancheg.model.data.AppState
import com.banancheg.model.data.DataModel

class RepositoryImplLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDb(appState: AppState) {
        dataSource.saveToDb(appState)
    }

    override suspend fun getAllHistory(): List<DataModel> {
        return dataSource.getAllHistory()
    }
}