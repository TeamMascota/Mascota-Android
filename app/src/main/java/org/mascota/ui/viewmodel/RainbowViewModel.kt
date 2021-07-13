package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.data.repository.rainbow.RainbowRepository
import org.mascota.ui.view.rainbow.data.datasource.TempRainbowDataSource
import org.mascota.ui.view.rainbow.data.model.HelpInfoData
import org.mascota.ui.view.rainbow.data.model.RainbowInfoData
import org.mascota.ui.view.rainbow.farewell.data.datasource.FarewellDataSource
import org.mascota.ui.view.rainbow.farewell.data.model.PetInfoData

class RainbowViewModel(private val rainbowRepository: RainbowRepository, private val tempRainbowDataSource: TempRainbowDataSource, private val farewellDataSource: FarewellDataSource) : ViewModel() {
    private val _rainbowInfo = MutableLiveData<RainbowInfoData>()
    val rainbowInfo: LiveData<RainbowInfoData>
        get() = _rainbowInfo

    private val _helpInfo = MutableLiveData<List<HelpInfoData>>()
    val helpInfo: LiveData<List<HelpInfoData>>
        get() = _helpInfo


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

    fun getHelpInfo() = viewModelScope.launch {
        runCatching { tempRainbowDataSource.getHelpInfoData() }
            .onSuccess {
                _helpInfo.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getRainbowInfo() = viewModelScope.launch {
        runCatching { tempRainbowDataSource.getRainbowInfoData() }
            .onSuccess {
                _rainbowInfo.postValue(it)
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

}