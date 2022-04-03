package com.banancheg.translator.model.datasource

import com.banancheg.translator.model.data.DataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val REQUEST_SEARCH = "words/search"

        private const val QUERY_SEARCH = "search"
    }

    @GET(REQUEST_SEARCH)
    fun search(@Query(QUERY_SEARCH) word: String): Observable<List<DataModel>>
}