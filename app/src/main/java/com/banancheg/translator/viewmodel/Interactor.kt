package com.banancheg.translator.viewmodel


interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
    suspend fun getAllHistory(): T
}