package com.banancheg.repository

import com.banancheg.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDb(appState: AppState)
    suspend fun getAllHistory(): T
}