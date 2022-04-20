package com.banancheg.translator.di

import androidx.room.Room
import com.banancheg.historyscreen.history.HistoryActivity
import com.banancheg.historyscreen.history.HistoryInteractor
import com.banancheg.historyscreen.history.HistoryViewModel
import com.banancheg.model.data.SearchResultDto
import com.banancheg.repository.*
import com.banancheg.repository.db.MIGRATION_1_2
import com.banancheg.repository.db.TranslatorDataBase
import com.banancheg.translator.view.main.MainActivity
import com.banancheg.translator.view.main.MainInteractor
import com.banancheg.translator.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), TranslatorDataBase::class.java, "TranslatorDB")
        .addMigrations(MIGRATION_1_2)
        .build() }

    single { get<TranslatorDataBase>().historyDao() }
    single<Repository<List<SearchResultDto>>> { RepositoryImpl(RetrofitImpl()) }
    single<RepositoryLocal<List<SearchResultDto>>> { RepositoryImplLocal(RoomDataBaseImpl(historyDao = get())) }
}

val mainScreen = module {
    scope<MainActivity> {
        scoped { MainInteractor(repositoryRemote = get(), repositoryLocal = get()) }
        viewModel { MainViewModel(interactor = get()) }
    }
}

val historyScreen = module {
    scope<HistoryActivity> {
        scoped { HistoryInteractor(repositoryRemote = get(), repositoryLocal = get()) }
        viewModel { HistoryViewModel(interactor = get()) }
    }
}