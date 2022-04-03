package com.banancheg.translator.view.base

import androidx.appcompat.app.AppCompatActivity
import com.banancheg.translator.model.data.AppState
import com.banancheg.translator.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    abstract val viewModel: BaseViewModel<T>
}