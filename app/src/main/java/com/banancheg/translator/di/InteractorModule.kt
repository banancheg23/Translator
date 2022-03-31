package com.banancheg.translator.di

import NAME_LOCAL
import NAME_REMOTE
import com.banancheg.translator.model.data.AppState
import com.banancheg.translator.model.data.DataModel
import com.banancheg.translator.model.datasource.Repository
import com.banancheg.translator.viewmodel.Interactor
import com.banancheg.translator.view.main.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) remoteRepository: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) localRepository: Repository<List<DataModel>>
    ): Interactor<AppState> = MainInteractor(remoteRepository, localRepository)
}