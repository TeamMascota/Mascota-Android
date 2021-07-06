package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.ui.view.home.data.datasource.HomeBookDataSource
import org.mascota.ui.view.home.data.model.HomeBookInfoData
import org.mascota.ui.view.home.data.model.HomeContentInfoData

class HomeViewModel(private val homeBookDataSource: HomeBookDataSource) : ViewModel() {
    private val _homeBookInfo = MutableLiveData<HomeBookInfoData>()
    val homeBookInfo: LiveData<HomeBookInfoData>
    get() = _homeBookInfo

    private val _homeContent = MutableLiveData<List<HomeContentInfoData>>()
    val homeContent: LiveData<List<HomeContentInfoData>>
    get() = _homeContent

    fun getHomeBookInfo() = viewModelScope.launch {
        runCatching { homeBookDataSource.getHomeBookInfoData() }
            .onSuccess {
                _homeBookInfo.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getHomeContentInfo() = viewModelScope.launch {
        runCatching { homeBookDataSource.getHomeContentInfoData()}
            .onSuccess {
                _homeContent.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }


}