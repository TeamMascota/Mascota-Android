package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.data.remote.model.request.user.ReqSignIn
import org.mascota.data.remote.model.request.user.ReqSignUp
import org.mascota.data.repository.user.UserRepository
import org.mascota.util.Event

class UserViewModel(private val userRepository : UserRepository) : ViewModel() {

    //회원가입
    private val _signUpEvent = MutableLiveData<Event<Boolean>>()
    val signUpEvent : LiveData<Event<Boolean>> = _signUpEvent

    private val _idData = MutableLiveData<String>()

    private val _passData = MutableLiveData<String>()

    private val _nextBtnEnableEvent = MutableLiveData<Event<Boolean>>()
    val nextBtnEnableEvent: LiveData<Event<Boolean>>
        get() = _nextBtnEnableEvent

    fun postId(id: String) {
        _idData.postValue(id)
    }

    fun postPass(pass: String) {
        _passData.postValue(pass)
    }

    fun postBtnEnable(isEnable: Boolean) {
        _nextBtnEnableEvent.postValue(Event(isEnable))
    }

    //로그인

    private val _signInEvent = MutableLiveData<Event<Boolean>>()
    val signInEvent : LiveData<Event<Boolean>> = _signInEvent

    private val _idLogin = MutableLiveData<String>()
    private val _pwdLogin = MutableLiveData<String>()


    private val _nextSignInEnaleEvent = MutableLiveData<Event<Boolean>>()
    val nextSignInEnaleEvent : LiveData<Event<Boolean>>
    get() = _nextSignInEnaleEvent

    fun postLoginId(id: String){
        _idLogin.postValue(id)
    }

    fun postLoginPwd(pass: String){
        _pwdLogin.postValue(pass)
    }


    fun postSignUp() = viewModelScope.launch {
        runCatching { userRepository.postSignUp(ReqSignUp(requireNotNull(_idData.value), requireNotNull(_passData.value))) }
            .onSuccess {
                _signUpEvent.postValue(Event(true))
            }
            .onFailure {
                _signUpEvent.postValue(Event(false))
                it.printStackTrace()
            }
    }

    fun postSignIn() = viewModelScope.launch {
        kotlin.runCatching { userRepository.postSignIn(ReqSignIn(requireNotNull(_idLogin.value),
            requireNotNull(_pwdLogin.value))) }
            .onSuccess {
                _signInEvent.postValue(Event(true))

            }
            .onFailure{
                _signInEvent.postValue(Event(false))
                it.printStackTrace()
            }
            }

}