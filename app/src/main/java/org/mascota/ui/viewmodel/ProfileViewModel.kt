package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.mascota.data.repository.profile.ProfileRepository
import org.mascota.util.Event

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    private val _nextBtnEnableEvent = MutableLiveData<Event<Boolean>>()
    val nextBtnEnableEvent: LiveData<Event<Boolean>>
        get() = _nextBtnEnableEvent

    fun postBtnEnable(isEnable: Boolean) =
        viewModelScope.launch { _nextBtnEnableEvent.postValue(Event(isEnable)) }

    fun postRegisterPet(imageList: MutableList<MultipartBody.Part>) {}
}