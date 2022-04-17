package com.banancheg.repository


interface Repository<T> {
    suspend fun getData(word: String): T
}