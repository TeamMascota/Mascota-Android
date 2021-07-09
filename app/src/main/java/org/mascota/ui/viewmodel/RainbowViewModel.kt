package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.ui.view.rainbow.data.datasource.RainbowDataSource
import org.mascota.ui.view.rainbow.data.model.HelpInfoData
import org.mascota.ui.view.rainbow.data.model.RainbowInfoData
import org.mascota.ui.view.rainbow.farewell.data.datasource.FarewellDataSource
import org.mascota.ui.view.rainbow.farewell.data.model.PetInfoData

class RainbowViewModel(private val rainbowDataSource: RainbowDataSource, private val farewellDataSource: FarewellDataSource) : ViewModel() {
    private val _rainbowInfo = MutableLiveData<RainbowInfoData>()
    val rainbowInfo: LiveData<RainbowInfoData>
        get() = _rainbowInfo

    private val _helpInfo = MutableLiveData<List<HelpInfoData>>()
    val helpInfo: LiveData<List<HelpInfoData>>
        get() = _helpInfo

    private val _loveMoment =MutableLiveData<RainbowInfoData>()
    val loveMoment : MutableLiveData<RainbowInfoData>
            get() = _loveMoment

    fun getloveoMent() = viewModelScope.launch {
        kotlin.runCatching { rainbowDataSource.getLoveBestMomentData() }
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
        runCatching { rainbowDataSource.getHelpInfoData() }
            .onSuccess {
                _helpInfo.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getRainbowInfo() = viewModelScope.launch {
        runCatching { rainbowDataSource.getRainbowInfoData() }
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