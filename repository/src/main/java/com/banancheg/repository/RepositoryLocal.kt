package com.banancheg.repository

import com.banancheg.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDb(appState: AppState)
    suspend fun getAllHistory(): T
}