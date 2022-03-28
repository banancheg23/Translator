package com.banancheg.translator.model.datasource

import com.banancheg.translator.model.data.DataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun search(@Query("search") word: String): Observable<List<DataModel>>
}