package com.banancheg.translator.view.history

import androidx.lifecycle.LiveData
import com.banancheg.translator.model.data.AppState
import com.banancheg.translator.utils.parseLocalSearchResults
import com.banancheg.translator.viewmodel.BaseViewModel
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