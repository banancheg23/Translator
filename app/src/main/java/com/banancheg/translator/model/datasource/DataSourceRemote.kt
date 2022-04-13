package com.banancheg.translator.model.datasource

import com.banancheg.translator.model.data.DataModel

class DataSourceRemote(private val remoteProvider: RetrofitImpl) : DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
}