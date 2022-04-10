package com.banancheg.translator.model.datasource

import com.banancheg.translator.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDb(appState: AppState)
}