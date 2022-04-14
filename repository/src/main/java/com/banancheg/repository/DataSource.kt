package com.banancheg.repository


interface DataSource<T> {
    suspend fun getData(word: String): T
}