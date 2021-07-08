package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.ui.view.rainbow.data.datasource.RainbowDataSource
import org.mascota.ui.view.rainbow.data.model.HelpInfoData
import org.mascota.ui.view.rainbow.data.model.RainbowInfoData

class RainbowViewModel(private val rainbowDataSource: RainbowDataSource) : ViewModel() {
    private val _rainbowInfo = MutableLiveData<RainbowInfoData>()
    val rainbowInfo: LiveData<RainbowInfoData>
        get() = _rainbowInfo

    private val _helpInfo = MutableLiveData<List<HelpInfoData>>()
    val helpInfo: LiveData<List<HelpInfoData>>
        get() = _helpInfo

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

}