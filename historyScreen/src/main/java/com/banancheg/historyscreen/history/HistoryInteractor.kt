package com.banancheg.historyscreen.history

import com.banancheg.core.viewmodel.Interactor
import com.banancheg.model.data.AppState
import com.banancheg.model.data.SearchResultDto
import com.banancheg.repository.Repository
import com.banancheg.repository.RepositoryLocal
import com.banancheg.utils.mapSearchResultToResult

class HistoryInteractor(
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }

    override suspend fun getAllHistory(): AppState {
        return AppState.Success(mapSearchResultToResult(repositoryLocal.getAllHistory()))
    }
}