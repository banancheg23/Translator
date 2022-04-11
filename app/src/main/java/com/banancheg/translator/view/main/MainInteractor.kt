package com.banancheg.translator.view.main

import com.banancheg.translator.model.data.AppState
import com.banancheg.translator.model.data.DataModel
import com.banancheg.translator.model.datasource.Repository
import com.banancheg.translator.model.datasource.RepositoryLocal
import com.banancheg.translator.viewmodel.Interactor

class MainInteractor (
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState

        if (fromRemoteSource) {
            appState = AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDb(appState)
        } else {
            appState = AppState.Success(repositoryLocal.getData(word))
        }

        return appState
    }

    override suspend fun getAllHistory(): AppState {
        return AppState.Success(repositoryLocal.getAllHistory())
    }
}
