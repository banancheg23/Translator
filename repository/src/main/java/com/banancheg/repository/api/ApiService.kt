package com.banancheg.repository.api

import com.banancheg.model.data.SearchResultDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val REQUEST_SEARCH = "words/search"

        private const val QUERY_SEARCH = "search"
    }

    @GET(REQUEST_SEARCH)
    fun searchAsync(@Query(QUERY_SEARCH) word: String): Deferred<List<SearchResultDto>>
}