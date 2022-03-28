package com.banancheg.translator.view.base

import com.banancheg.translator.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}