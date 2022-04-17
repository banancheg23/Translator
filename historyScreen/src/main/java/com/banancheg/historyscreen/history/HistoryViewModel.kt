package com.banancheg.historyscreen.history

import androidx.lifecycle.LiveData
import com.banancheg.core.viewmodel.BaseViewModel
import com.banancheg.model.data.AppState
import com.banancheg.utils.parseLocalSearchResults
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interactor: HistoryInteractor
) : BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe() = liveDataForViewToObserve

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun getData(word: String, isOnline: Boolean) {
        job = viewModelCoroutineScope.launch {
            _mutableLiveData.postValue(parseLocalSearchResults(interactor.getAllHistory()))
        }
    }

    override fun onCleared() {
        _mutableLiveData.postValue(AppState.Success(null))
        super.onCleared()
    }
}