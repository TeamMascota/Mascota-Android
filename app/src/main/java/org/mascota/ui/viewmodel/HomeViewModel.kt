package org.mascota.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.data.local.MascotaSharedPreference.getUserId
import org.mascota.data.remote.model.response.home.ResHomePart1
import org.mascota.data.repository.home.HomeRepository
import org.mascota.ui.view.home.data.datasource.HomeBookDataSource
import org.mascota.ui.view.home.data.model.HomeBookInfoData
import org.mascota.ui.view.home.data.model.HomeContentInfoData
import org.mascota.ui.view.home.data.model.HomeDiaryInfoData

class HomeViewModel(private val homeRepository: HomeRepository, private val homeBookDataSource: HomeBookDataSource) : ViewModel() {
    private val _homeBookInfo = MutableLiveData<HomeBookInfoData>()
    val homeBookInfo: LiveData<HomeBookInfoData>
        get() = _homeBookInfo

    private val _homeDiaryInfo = MutableLiveData<HomeDiaryInfoData>()
    val homeDiaryInfo: LiveData<HomeDiaryInfoData>
        get() = _homeDiaryInfo

    private val _homeContent = MutableLiveData<List<HomeContentInfoData>>()
    val homeContent: LiveData<List<HomeContentInfoData>>
        get() = _homeContent

    private val _homePart1 = MutableLiveData<ResHomePart1>()
    val homePart1: LiveData<ResHomePart1>
        get() = _homePart1

    /*
    fun getHomeBookInfo() = viewModelScope.launch {
        runCatching { homeBookDataSource.getHomeBookInfoData() }
            .onSuccess {
                _homeBookInfo.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getHomeDiaryInfo() = viewModelScope.launch {
        runCatching { homeBookDataSource.getHomeDiaryInfoData() }
            .onSuccess {
                _homeDiaryInfo.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getHomeContentInfo() = viewModelScope.launch {
        runCatching { homeBookDataSource.getHomeContentInfoData() }
            .onSuccess {
                _homeContent.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }*/

    fun getResHomePart1() = viewModelScope.launch {
        runCatching { homeRepository.getHomePart1(getUserId())}
            .onSuccess {
                _homePart1.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

}