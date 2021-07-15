package org.mascota.ui.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.mascota.data.local.MascotaSharedPreference.getUserId
import org.mascota.data.repository.profile.ProfileRepository
import org.mascota.ui.view.profile.data.model.Pets
import org.mascota.util.Event

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    private val emptyInfoData = Pets("", -1, "", -1)
    val petInfoDataList = mutableListOf<Pets>()

    var writer = ""
    lateinit var imgUri : Uri
    var petNum = 0
    var title = "0"

    val imageUriList = mutableListOf<Uri?>()

    private val _nextBtnEnableEvent = MutableLiveData<Event<Boolean>>()
    val nextBtnEnableEvent: LiveData<Event<Boolean>>
        get() = _nextBtnEnableEvent

    private val _imageList = mutableListOf<MultipartBody.Part?>()

    private val _petInfo = MutableLiveData<MutableList<HashMap<String, RequestBody>>>()
    val petInfo: LiveData<MutableList<HashMap<String, RequestBody>>>
        get() = _petInfo

    fun postBtnEnable(isNotEmpty : Boolean) = _nextBtnEnableEvent.postValue(Event(isNotEmpty))

    fun postProfilePet() {
        viewModelScope.launch {
            runCatching {
                Log.d("hi", _imageList.size.toString())
                val pet = petInfoDataList.map{it.toRequestBody()}

                profileRepository.postRegisterPet(hashMapOf("userId" to getUserId().toRequestBody("text/plain".toMediaTypeOrNull()),
                    "pets" to pet.toString().toRequestBody("text/plain".toMediaTypeOrNull())
                    ), _imageList)}
                    .onSuccess { Log.d("fads",it.message) }
                    .onFailure { it.printStackTrace() }
        }
    }

    fun makeRequestBody(name: String, kind: Int, startDate: String, gender: Int) =
        Pets(name, kind, startDate, gender).toRequestBody()

    fun addImageList(image: MultipartBody.Part?) {
        _imageList.add(image)
    }

    fun setImageList(index: Int, image: MultipartBody.Part?) {
        Log.d("hi", _imageList.size.toString())
        _imageList[index] = image
    }

    fun addEmptyData() {
        petInfoDataList.add(emptyInfoData)
    }
}