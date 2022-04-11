package com.banancheg.translator.model.datasource

import com.banancheg.translator.model.data.AppState
import com.banancheg.translator.model.data.DataModel
import io.reactivex.Observable

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDb(appState: AppState)
    suspend fun getAllHistory(): T
}