package com.banancheg.translator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.banancheg.translator.model.data.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T: AppState>(
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    protected val viewModelCoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob() + coroutineExceptionHandler)

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    abstract fun handleError(error: Throwable)

    abstract fun getData(word: String, isOnline: Boolean)
}