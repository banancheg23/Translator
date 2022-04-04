package com.banancheg.translator.di

import NAME_LOCAL
import NAME_REMOTE
import com.banancheg.translator.model.data.DataModel
import com.banancheg.translator.model.datasource.Repository
import com.banancheg.translator.model.datasource.RepositoryImpl
import com.banancheg.translator.model.datasource.RetrofitImpl
import com.banancheg.translator.model.datasource.RoomDataBaseImpl
import com.banancheg.translator.view.main.MainInteractor
import com.banancheg.translator.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>> (named(NAME_REMOTE)) { RepositoryImpl(RetrofitImpl()) }

    single<Repository<List<DataModel>>> (named(NAME_LOCAL)) { RepositoryImpl(RoomDataBaseImpl()) }
}

val mainScreen = module {
    factory { MainInteractor(remoteRepository = get(named(NAME_REMOTE)), localRepository = get(named(NAME_LOCAL))
    ) }

    factory { MainViewModel(interactor = get()) }
}