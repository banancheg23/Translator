package com.banancheg.core.base

import com.banancheg.model.data.AppState
import com.banancheg.model.data.DataModel

interface View {
    fun renderData(appState: AppState)
    fun setDataToAdapter(data: List<DataModel>)
}