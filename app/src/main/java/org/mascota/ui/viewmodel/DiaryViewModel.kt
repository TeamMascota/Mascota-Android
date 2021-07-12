package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.util.Event

class DiaryViewModel : ViewModel() {
    private val _selectedEmotion = MutableLiveData<Int>()
    val selectedEmotion: LiveData<Int>
        get() = _selectedEmotion

    private val _nextBtnEnableEvent = MutableLiveData<Event<Boolean>>()
    val nextBtnEnableEvent: LiveData<Event<Boolean>>
        get() = _nextBtnEnableEvent

    fun postBtnEnable(isEnable: Boolean) =
        viewModelScope.launch { _nextBtnEnableEvent.postValue(Event(isEnable)) }

    fun postSelectedEmotion(emotion: Int) =
        viewModelScope.launch { _selectedEmotion.postValue(emotion) }
}