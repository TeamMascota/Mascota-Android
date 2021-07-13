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