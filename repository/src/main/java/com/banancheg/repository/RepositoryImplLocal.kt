package com.banancheg.repository

import com.banancheg.model.data.AppState
import com.banancheg.model.data.SearchResultDto

class RepositoryImplLocal(private val dataSource: DataSourceLocal<List<SearchResultDto>>) :
    RepositoryLocal<List<SearchResultDto>> {
    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDb(appState: AppState) {
        dataSource.saveToDb(appState)
    }

    override suspend fun getAllHistory(): List<SearchResultDto> {
        return dataSource.getAllHistory()
    }
}