package com.banancheg.translator.model.datasource

import com.banancheg.translator.model.data.DataModel
import io.reactivex.Observable

class RoomDataBaseImpl : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }
}