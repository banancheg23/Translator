package com.banancheg.translator.model.datasource

import com.banancheg.translator.model.data.AppState
import com.banancheg.translator.model.data.DataModel

class RepositoryImplLocal(private val dataSource: DataSourceLocal<List<DataModel>>) : RepositoryLocal<List<DataModel>> {
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