package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.data.local.MascotaSharedPreference.getUserId
import org.mascota.data.remote.model.response.rainbow.ResRainbowHome
import org.mascota.data.repository.rainbow.RainbowRepository
import org.mascota.ui.view.rainbow.farewell.data.datasource.FarewellDataSource
import org.mascota.ui.view.rainbow.farewell.data.model.PetInfoData

class RainbowViewModel(private val rainbowRepository: RainbowRepository, private val farewellDataSource: FarewellDataSource) : ViewModel() {
    private val _rainbowHomeInfo = MutableLiveData<ResRainbowHome>()
    val rainbowHomeInfo: LiveData<ResRainbowHome>
        get() = _rainbowHomeInfo


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