package com.banancheg.translator.view.base

import com.banancheg.translator.model.data.AppState
import com.banancheg.translator.model.data.DataModel

interface View {
    fun renderData(appState: AppState)
    fun setDataToAdapter(data: List<DataModel>)
}