package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.util.Event

class DiaryViewModel : ViewModel() {
    private val _nextBtnEnableEvent = MutableLiveData<Event<Boolean>>()
    val nextBtnEnableEvent: LiveData<Event<Boolean>> = _nextBtnEnableEvent

    fun postBtnEnable(isEnable : Boolean) = viewModelScope.launch { _nextBtnEnableEvent.postValue(Event(isEnable)) }
}