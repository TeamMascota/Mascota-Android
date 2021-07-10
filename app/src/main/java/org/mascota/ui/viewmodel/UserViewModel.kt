package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _idInFo = MutableLiveData<String>()
    val idInfo : LiveData<String>
    get() = _idInFo

    fun postIdInFo(email : String){
        viewModelScope.launch {
            _idInFo.postValue(email)
        }

    }


}