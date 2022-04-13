package com.banancheg.translator.di

import NAME_LOCAL
import NAME_REMOTE
import androidx.room.Room
import com.banancheg.translator.db.MIGRATION_1_2
import com.banancheg.translator.db.TranslatorDataBase
import com.banancheg.translator.model.data.DataModel
import com.banancheg.translator.model.datasource.*
import com.banancheg.translator.view.history.HistoryInteractor
import com.banancheg.translator.view.history.HistoryViewModel
import com.banancheg.translator.view.main.MainInteractor
import com.banancheg.translator.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), TranslatorDataBase::class.java, "TranslatorDB")
        .addMigrations(MIGRATION_1_2)
        .build() }

    single { get<TranslatorDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImpl(RetrofitImpl()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplLocal(RoomDataBaseImpl(historyDao = get())) }
}

val mainScreen = module {
    factory { MainInteractor(repositoryRemote = get(), repositoryLocal = get()) }
    factory { MainViewModel(interactor = get()) }
}

val historyScreen = module {
    factory { HistoryInteractor(repositoryRemote = get(), repositoryLocal = get()) }
    factory { HistoryViewModel(interactor = get()) }
}