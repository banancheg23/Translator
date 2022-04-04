package com.banancheg.translator.model.datasource


interface Repository<T> {
    suspend fun getData(word: String): T
}