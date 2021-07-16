package org.mascota.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.data.local.MascotaSharedPreference.getPetId
import org.mascota.data.local.MascotaSharedPreference.getUserId
import org.mascota.data.remote.model.response.content.ResContentList
import org.mascota.data.remote.model.response.rainbow.ResBestMoment
import org.mascota.data.remote.model.response.rainbow.ResPetName
import org.mascota.data.remote.model.response.rainbow.ResFarewellSelect
import org.mascota.data.remote.model.response.rainbow.ResRainbowHome
import org.mascota.data.remote.model.request.rainbow.ReqRainbowEpilogue
import org.mascota.data.remote.model.response.rainbow.*
import org.mascota.data.repository.rainbow.RainbowRepository
import org.mascota.ui.view.rainbow.farewell.data.datasource.FarewellDataSource
import org.mascota.ui.view.rainbow.farewell.data.model.PetInfoData
import org.mascota.util.Event

class RainbowViewModel(private val rainbowRepository: RainbowRepository, private val farewellDataSource: FarewellDataSource) : ViewModel() {
    private val _rainbowHomeInfo = MutableLiveData<ResRainbowHome>()
    val rainbowHomeInfo: LiveData<ResRainbowHome>
        get() = _rainbowHomeInfo

    private val _fareWellQuit = MutableLiveData<ResFarewellQuit>()
    val fareWellQuit : LiveData<ResFarewellQuit>
    get() = _fareWellQuit

    private val _resBestMoment = MutableLiveData<ResBestMoment>()
    val resBestMoment: LiveData<ResBestMoment>
        get() = _resBestMoment

    private val _petName = MutableLiveData<ResPetName>()
    val petName : MutableLiveData<ResPetName>
    get() = _petName

    private val _rainbowContent = MutableLiveData<ResRainbowContent>()
    val rainbowContent : MutableLiveData<ResRainbowContent>
    get() = _rainbowContent

    private val _farewellAnimalList = MutableLiveData<List<ResFarewellSelect.Data>>()
    val farewellAnimalList: LiveData<List<ResFarewellSelect.Data>>
        get() = _farewellAnimalList


    private val _rainBowBookInfo = MutableLiveData<ResRainbowBook.Data>()
    val rainbowBookInfo : LiveData<ResRainbowBook.Data>
    get() = _rainBowBookInfo

    var name = ""

    fun deleteFareWellQuit() = viewModelScope.launch {
        kotlin.runCatching { rainbowRepository.deleteFarewellQuit(getPetId()) }
            .onSuccess {
                Log.d("이별준비 취소","성공")
                _fareWellQuit.postValue(it)
            }
            .onFailure {
                Log.d("이별준비 취소","실패")
                it.printStackTrace()
            }
    }
    fun putRainbowContent() = viewModelScope.launch {

        kotlin.runCatching { rainbowRepository.putRainbowContent(getPetId()) }
            .onSuccess {
                _rainbowContent.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }


    fun getResRainBowBestMoment() = viewModelScope.launch {
        runCatching { rainbowRepository.getRainbowBestMoment(getUserId(), getPetId()) }
            .onSuccess {
                _resBestMoment.postValue(it)
                Log.d("server", it.toString())
            }
            .onFailure {
                it.printStackTrace()
            }
    }


    private val _epliEvent = MutableLiveData<Event<Boolean>>()
    val epliEvent : LiveData<Event<Boolean>> = _epliEvent

    private val _title = MutableLiveData<String>()
    private val _content = MutableLiveData<String>()

    fun postTitle(title: String){
        _title.postValue(title)
    }

    fun postContent(content : String){
        _content.postValue(content)
    }

    private val _nextBtnEnaleEvent = MutableLiveData<Event<Boolean>>()
    val nextBtnEnaleEvent : LiveData<Event<Boolean>>
        get() = _nextBtnEnaleEvent



    fun postEpilogue()  = viewModelScope.launch {
        kotlin.runCatching {
            rainbowRepository.postRainbowEpilogue(
                getUserId(),
                getPetId(),
                ReqRainbowEpilogue(requireNotNull(_title.value), requireNotNull(_content.value))
            )
              }
            .onSuccess {
                _epliEvent.postValue(Event(true))
            }
            .onFailure {
                _epliEvent.postValue(Event(false))
                it.printStackTrace()

            }

    }


    fun getRainbowBookInfo() = viewModelScope.launch {
        kotlin.runCatching { rainbowRepository.getRainbowBook(getPetId()) }
            .onSuccess {
                _rainBowBookInfo.postValue(it.data)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getPetName() = viewModelScope.launch {
        //    //GET Rainbow Pet Name
        //    suspend fun getRainbowPetName(petId: String): ResPetName
        kotlin.runCatching { rainbowRepository.getRainbowPetName("60edf6e5e5003a744892ce39") }
            .onSuccess {
                _petName.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    private val _petInfo = MutableLiveData<PetInfoData>()
    val petInfo: LiveData<PetInfoData>
        get() = _petInfo

    fun getFarewellSelect() = viewModelScope.launch {
        runCatching { rainbowRepository.getFarewellSelect() }
            .onSuccess {
                _farewellAnimalList.postValue(it.data)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getPetInfo() = viewModelScope.launch {
        runCatching { farewellDataSource.getPetInfoData() }
            .onSuccess {
                _petInfo.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getRainbowHome() = viewModelScope.launch {
        runCatching { rainbowRepository.getRainbowHome(getUserId(), getPetId()) }
            .onSuccess {
                _rainbowHomeInfo.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

}