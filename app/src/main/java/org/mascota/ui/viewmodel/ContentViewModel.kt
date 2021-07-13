package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.data.remote.model.response.content.ResContentDetail
import org.mascota.data.repository.content.ContentRepository
import org.mascota.ui.view.content.detail.data.model.ContentDiaryInfoData

class ContentViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    private val _contentDetail = MutableLiveData<List<ContentDiaryInfoData>>()
    val contentDetail: LiveData<List<ContentDiaryInfoData>>
        get() = _contentDetail

    private val _resContentDetail = MutableLiveData<ResContentDetail>()
    val resContentDetail : LiveData<ResContentDetail>
        get() = _resContentDetail

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