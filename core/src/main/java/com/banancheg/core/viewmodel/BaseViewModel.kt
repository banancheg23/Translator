package com.banancheg.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.banancheg.model.data.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T: AppState>(
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    protected val viewModelCoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob() + coroutineExceptionHandler)
    protected var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        cancelJob()
        viewModelCoroutineScope.cancel()
    }

    protected fun cancelJob() {
        job?.cancel()
        job = null
    }

    abstract fun handleError(error: Throwable)

    abstract fun getData(word: String, isOnline: Boolean)
}