package com.banancheg.translator.di

import NAME_LOCAL
import NAME_REMOTE
import com.banancheg.translator.model.data.DataModel
import com.banancheg.translator.model.datasource.*
import com.banancheg.translator.view.main.MainInteractor
import com.banancheg.translator.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>> (named(NAME_REMOTE)) { RepositoryImpl(dataSource = get(named(NAME_REMOTE))) }

    single<Repository<List<DataModel>>> (named(NAME_LOCAL)) { RepositoryImpl(dataSource = get(named(NAME_LOCAL))) }
    
    single<DataSource<List<DataModel>>> (named(NAME_REMOTE)) { DataSourceRemote(RetrofitImpl()) }

    single<DataSource<List<DataModel>>> (named(NAME_LOCAL)) { DataSourceLocal(RoomDataBaseImpl()) }
}

val mainScreen = module {
    factory { MainInteractor(remoteRepository = get(named(NAME_REMOTE)), localRepository = get(named(NAME_LOCAL))
    ) }

    factory { MainViewModel(interactor = get()) }
}