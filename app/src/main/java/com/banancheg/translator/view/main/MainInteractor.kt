package com.banancheg.translator.view.main

import com.banancheg.core.viewmodel.Interactor
import com.banancheg.model.data.AppState
import com.banancheg.model.data.SearchResultDto
import com.banancheg.repository.Repository
import com.banancheg.repository.RepositoryLocal
import com.banancheg.utils.mapSearchResultToResult

class MainInteractor (
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState

        if (fromRemoteSource) {
            appState = AppState.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
            repositoryLocal.saveToDb(appState)
        } else {
            appState = AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
        }

        return appState
    }

    override suspend fun getAllHistory(): AppState {
        return AppState.Success(mapSearchResultToResult(repositoryLocal.getAllHistory()))
    }
}
