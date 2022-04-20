package com.banancheg.repository

import com.banancheg.model.data.SearchResultDto

class RepositoryImpl(private val dataSource: DataSource<List<SearchResultDto>>) :
    Repository<List<SearchResultDto>> {
    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }
}