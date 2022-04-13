package com.banancheg.translator.model.datasource

import com.banancheg.translator.model.data.DataModel

class RepositoryImpl(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}