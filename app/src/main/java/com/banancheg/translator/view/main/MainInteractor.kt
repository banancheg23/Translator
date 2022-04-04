package com.banancheg.translator.view.main

import com.banancheg.translator.model.data.AppState
import com.banancheg.translator.model.data.DataModel
import com.banancheg.translator.model.datasource.Repository
import com.banancheg.translator.viewmodel.Interactor

class MainInteractor (
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean) = AppState.Success(
        if (fromRemoteSource) {
            remoteRepository
        } else {
            localRepository
        }.getData(word)
    )
}
