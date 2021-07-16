package org.mascota.ui.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.mascota.data.local.MascotaSharedPreference.getUserId
import org.mascota.data.local.MascotaSharedPreference.setIsProfileCreate
import org.mascota.data.local.MascotaSharedPreference.setPetId
import org.mascota.data.remote.model.request.profile.ReqRegisterBook
import org.mascota.data.remote.model.request.profile.ReqRegisterPet
import org.mascota.data.repository.profile.ProfileRepository
import org.mascota.ui.view.profile.data.model.Pets
import org.mascota.util.Event

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    private val emptyInfoData = ReqRegisterPet.Pet("", -1, "", -1)
    val petInfoDataList = mutableListOf<ReqRegisterPet.Pet>()

    var petId = ""
    var writer = ""
    var prologueTitle = ""
    var prologue = ""
    lateinit var imgUri: Uri
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

    fun postBtnEnable(isNotEmpty: Boolean) = _nextBtnEnableEvent.postValue(Event(isNotEmpty))

    fun postProfilePet() {
        viewModelScope.launch {
            runCatching {
                //val pet = petInfoDataList.map { it.toRequestBody() }

                val pet = ReqRegisterPet(petInfoDataList, getUserId())
                viewModelScope.launch {
                    runCatching {
                        profileRepository.postRegisterPet(pet)
                    }
                        .onSuccess {
                            petId = it.data.petId[0]
                            Log.d("fads", it.message)
                        }
                        .onFailure { it.printStackTrace() }
                }
            }
        }
    }

    fun postProlog() {
        viewModelScope.launch {
            runCatching {
                profileRepository.postRegisterBook(
                    getUserId(),
                    ReqRegisterBook(imgUri.toString(), title, writer, prologueTitle, prologue)
                )
            }
                .onSuccess {
                    setPetId(petId)
                    setIsProfileCreate(true)
                }
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