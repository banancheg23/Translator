package com.banancheg.translator.model.datasource

import io.reactivex.Observable

interface Repository<T> {
    fun getData(word: String): Observable<T>
}