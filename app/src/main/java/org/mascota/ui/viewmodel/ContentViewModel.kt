package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.data.local.MascotaSharedPreference.getUserId
import org.mascota.data.remote.model.request.content.ReqContent
import org.mascota.data.remote.model.response.content.ResContentDetail
import org.mascota.data.remote.model.response.content.ResContentList
import org.mascota.data.repository.content.ContentRepository
import org.mascota.ui.view.content.detail.data.model.ContentDiaryInfoData

class ContentViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    private val _contentDetail = MutableLiveData<List<ContentDiaryInfoData>>()
    val contentDetail: LiveData<List<ContentDiaryInfoData>>
        get() = _contentDetail

    private val _resContentDetail = MutableLiveData<ResContentDetail>()
    val resContentDetail: LiveData<ResContentDetail>
        get() = _resContentDetail

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
        runCatching {
            contentRepository.putContentEdit(
                requireNotNull(_chapterId.value),
                ReqContent(requireNotNull(_chapterTitle.value))
            )
        }
            .onSuccess { }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun postContentAdd() = viewModelScope.launch {
        runCatching {
            contentRepository.postContentAdd(
                getUserId(),
                ReqContent(requireNotNull(_chapterTitle.value))
            )
        }
            .onSuccess {
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getResContentList() = viewModelScope.launch {
        runCatching { contentRepository.getContentList(getUserId()) }
            .onSuccess {
                Log.d("list", "success")
                _resContentList.postValue(it)
            }
            .onFailure {
                Log.d("list", "fail")
                it.printStackTrace()
            }
    }

    fun getContentDetail(chapterId: String) = viewModelScope.launch {
        runCatching { contentRepository.getContentDetail(chapterId) }
            .onSuccess {
                _resContentDetail.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }
}