package com.banancheg.translator.model.datasource

import com.banancheg.translator.model.data.DataModel
import io.reactivex.Observable

class DataSourceLocal : DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        TODO("Not yet implemented")
    }
}