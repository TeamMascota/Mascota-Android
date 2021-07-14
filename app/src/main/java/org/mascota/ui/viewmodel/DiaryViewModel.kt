package org.mascota.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.data.remote.model.response.diary.ResDiaryRead
import org.mascota.data.remote.model.response.home.ResHomePart1
import org.mascota.data.repository.diary.DiaryRepository
import org.mascota.util.Event

class DiaryViewModel(private val diaryRepository: DiaryRepository) : ViewModel() {
    private val _selectedEmotion = MutableLiveData<Int>()
    val selectedEmotion: LiveData<Int>
        get() = _selectedEmotion

    private val _nextBtnEnableEvent = MutableLiveData<Event<Boolean>>()
    val nextBtnEnableEvent: LiveData<Event<Boolean>>
        get() = _nextBtnEnableEvent

    private val _diaryReadPet = MutableLiveData<ResDiaryRead>()
    val diaryReadPet: LiveData<ResDiaryRead>
        get() = _diaryReadPet

    private val _petDiaryId = MutableLiveData<String>()

    fun postPetDiaryId(petDiaryId: String) {
        _petDiaryId.postValue(petDiaryId)
    }

    fun getPetDiaryRead() = viewModelScope.launch {
        runCatching { diaryRepository.getPetDiaryRead(requireNotNull("60edf95de5003a744892ce3d")) }
            .onSuccess {
                _diaryReadPet.postValue(it)
                Log.d("server", it.toString())
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun postBtnEnable(isEnable: Boolean) =
        viewModelScope.launch { _nextBtnEnableEvent.postValue(Event(isEnable)) }

    fun postSelectedEmotion(emotion: Int) =
        viewModelScope.launch { _selectedEmotion.postValue(emotion) }
}