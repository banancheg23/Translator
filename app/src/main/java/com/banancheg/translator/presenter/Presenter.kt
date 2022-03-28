package com.banancheg.translator.presenter

import com.banancheg.translator.model.data.AppState
import com.banancheg.translator.view.base.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}