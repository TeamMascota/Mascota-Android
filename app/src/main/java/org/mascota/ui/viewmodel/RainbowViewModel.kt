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
import org.mascota.data.repository.rainbow.RainbowRepository
import org.mascota.ui.view.rainbow.farewell.data.datasource.FarewellDataSource
import org.mascota.ui.view.rainbow.farewell.data.model.PetInfoData

class RainbowViewModel(private val rainbowRepository: RainbowRepository, private val farewellDataSource: FarewellDataSource) : ViewModel() {
    private val _rainbowHomeInfo = MutableLiveData<ResRainbowHome>()
    val rainbowHomeInfo: LiveData<ResRainbowHome>
        get() = _rainbowHomeInfo

    private val _bestMoment =MutableLiveData<ResBestMoment>()
    val bestMoment : MutableLiveData<ResBestMoment>
        get() = _bestMoment

    private val _petName = MutableLiveData<ResPetName>()
    val petName : MutableLiveData<ResPetName>
    get() = _petName

    private val _farewellAnimalList = MutableLiveData<List<ResFarewellSelect.Data.Pet>>()
    val farewellAnimalList: LiveData<List<ResFarewellSelect.Data.Pet>>
        get() = _farewellAnimalList

    private val _resBestMoment = MutableLiveData<ResBestMoment>()
    val resBestMoment: LiveData<ResBestMoment>
        get() = _resBestMoment

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

    fun getPetName() = viewModelScope.launch {
        //    //GET Rainbow Pet Name
        //    suspend fun getRainbowPetName(petId: String): ResPetName
        kotlin.runCatching { rainbowRepository.getRainbowPetName("60ed4359e5003a744892ce2b") }
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
                Log.d("server-farewell-select", it.toString())
                _farewellAnimalList.postValue(it.data.pet)
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