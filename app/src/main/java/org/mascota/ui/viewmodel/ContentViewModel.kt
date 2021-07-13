package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.data.repository.content.ContentRepository
import org.mascota.ui.view.content.detail.data.datasource.ContentDetailDataSource
import org.mascota.ui.view.content.detail.data.model.ContentDiaryInfoData
import org.mascota.ui.view.content.detail.data.model.ContentMonthInfoData

class ContentViewModel(private val contentRepository: ContentRepository, private val contentDetailDataSource: ContentDetailDataSource) : ViewModel() {
    private val _contentDetail = MutableLiveData<List<ContentDiaryInfoData>>()
    val contentDetail: LiveData<List<ContentDiaryInfoData>>
        get() = _contentDetail

    private val _contentDetailMonth = MutableLiveData<List<ContentMonthInfoData>>()
    val contentDetailMonth: LiveData<List<ContentMonthInfoData>>
        get() = _contentDetailMonth

    fun getContentDetailInfo() = viewModelScope.launch {
        runCatching { contentDetailDataSource.getContentDiaryInfoData() }
            .onSuccess {
                _contentDetail.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getContentDetailMonthInfo() = viewModelScope.launch {
        runCatching { contentDetailDataSource.getContentMonthInfoData()}
            .onSuccess {
                _contentDetailMonth.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }
}