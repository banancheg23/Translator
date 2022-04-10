package com.banancheg.translator.app

import android.app.Application
import com.banancheg.translator.di.application
import com.banancheg.translator.di.historyScreen
import com.banancheg.translator.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TranslatorApp)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}