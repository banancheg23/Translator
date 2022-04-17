package com.banancheg.historyscreen.history

import com.banancheg.core.viewmodel.Interactor
import com.banancheg.model.data.AppState
import com.banancheg.model.data.DataModel
import com.banancheg.repository.Repository
import com.banancheg.repository.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }

    override suspend fun getAllHistory(): AppState {
        return AppState.Success(repositoryLocal.getAllHistory())
    }
}