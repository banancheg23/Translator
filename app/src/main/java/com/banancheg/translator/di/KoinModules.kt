package com.banancheg.translator.di

import androidx.room.Room
import com.banancheg.historyscreen.history.HistoryInteractor
import com.banancheg.historyscreen.history.HistoryViewModel
import com.banancheg.model.data.DataModel
import com.banancheg.repository.*
import com.banancheg.repository.db.MIGRATION_1_2
import com.banancheg.repository.db.TranslatorDataBase
import com.banancheg.translator.view.main.MainInteractor
import com.banancheg.translator.view.main.MainViewModel
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