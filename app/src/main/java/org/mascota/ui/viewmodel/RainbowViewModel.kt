package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.data.local.MascotaSharedPreference.getUserId
import org.mascota.data.remote.model.response.rainbow.ResBestMoment
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


    private val _loveMoment =MutableLiveData<RainbowInfoData>()
    val loveMoment : MutableLiveData<RainbowInfoData>
            get() = _loveMoment
    
    private val _joyMoment = MutableLiveData<RainbowInfoData>()
    val joyMoment : MutableLiveData<RainbowInfoData>
    get() = _joyMoment


    private val _angryMoment = MutableLiveData<RainbowInfoData>()
    val angryMoment : MutableLiveData<RainbowInfoData>
    get() = _angryMoment


    private val _usualMoment = MutableLiveData<RainbowInfoData>()
    val usualMoment : MutableLiveData<RainbowInfoData>
    get() = _usualMoment


    private val _sadMoment = MutableLiveData<RainbowInfoData>()
    val sadMoment : MutableLiveData<RainbowInfoData>
    get() = _sadMoment

    private val _boringMoment = MutableLiveData<RainbowInfoData>()
    val boringMoment : MutableLiveData<RainbowInfoData>
    get() = _boringMoment

    fun getBestMoment() = viewModelScope.launch {
        kotlin.runCatching { rainbowRepository.getRainbowBestMoment(getUserId(), "60ed4359e5003a744892ce2b")}
            .onSuccess {
                _bestMoment.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }


    fun getSadMoment() = viewModelScope.launch {
        kotlin.runCatching { tempRainbowDataSource.getSadMomentData() }
            .onSuccess {
                _sadMoment.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getBoringMoment() = viewModelScope.launch {
        kotlin.runCatching { tempRainbowDataSource.getBoringMomentData() }
            .onSuccess {
                _boringMoment.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getUsualMoment() = viewModelScope.launch {
        kotlin.runCatching { tempRainbowDataSource.getUsualMomentData() }
            .onSuccess {
                _usualMoment.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }


    fun getAngryMoment() = viewModelScope.launch {
        kotlin.runCatching { tempRainbowDataSource.getAngryBestMomentData() }
            .onSuccess {
                _angryMoment.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }


    }
    fun getJoyMent() = viewModelScope.launch {
        kotlin.runCatching { tempRainbowDataSource.getJoyBestMomentData() }
            .onSuccess {
                _joyMoment.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }


    fun getloveoMent() = viewModelScope.launch {
        kotlin.runCatching { tempRainbowDataSource.getLoveBestMomentData() }
            .onSuccess {
                _loveMoment.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }


    private val _petInfo = MutableLiveData<PetInfoData>()
    val petInfo: LiveData<PetInfoData>
        get() = _petInfo


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
        runCatching { rainbowRepository.getRainbowHome(getUserId(), "60ed4359e5003a744892ce2b") }
            .onSuccess {
                _rainbowHomeInfo.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

}