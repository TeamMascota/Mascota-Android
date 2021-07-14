package org.mascota.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.data.local.MascotaSharedPreference.getUserId
import org.mascota.data.remote.model.request.content.ReqContent
import org.mascota.data.remote.model.response.content.ResContentList
import org.mascota.data.repository.content.ContentRepository
import org.mascota.ui.view.content.detail.data.datasource.ContentDetailDataSource
import org.mascota.ui.view.content.detail.data.model.ContentDiaryInfoData
import org.mascota.ui.view.content.detail.data.model.ContentMonthInfoData
import org.mascota.util.Event

class ContentViewModel(private val contentRepository: ContentRepository, private val contentDetailDataSource: ContentDetailDataSource) : ViewModel() {
    private val _contentDetail = MutableLiveData<List<ContentDiaryInfoData>>()
    val contentDetail: LiveData<List<ContentDiaryInfoData>>
        get() = _contentDetail

    private val _contentDetailMonth = MutableLiveData<List<ContentMonthInfoData>>()
    val contentDetailMonth: LiveData<List<ContentMonthInfoData>>
        get() = _contentDetailMonth

    private val _resContentList = MutableLiveData<ResContentList>()
    val resContentList: LiveData<ResContentList>
        get() = _resContentList


    private val _chapterTitle = MutableLiveData<String>()

    fun postChapterTitle(chapterTitle: String) {
        _chapterTitle.postValue(chapterTitle)
    }

    private val _chapterId = MutableLiveData<String>()

    fun postChapterId(chapterId: String) {
        _chapterId.postValue(chapterId)
    }

    fun deleteContent() = viewModelScope.launch {
        runCatching { contentRepository.deleteContent(requireNotNull(_chapterId.value)) }
            .onSuccess { }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun putContentEdit() = viewModelScope.launch {
        runCatching { contentRepository.putContentEdit(requireNotNull(_chapterId.value), ReqContent(requireNotNull(_chapterTitle.value)))}
            .onSuccess {  }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun postContentAdd() = viewModelScope.launch {
        runCatching { contentRepository.postContentAdd(getUserId(), ReqContent(requireNotNull(_chapterTitle.value)))}
            .onSuccess {
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getResContentList() = viewModelScope.launch {
        runCatching { contentRepository.getContentList(getUserId()) }
            .onSuccess {
                _resContentList.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

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