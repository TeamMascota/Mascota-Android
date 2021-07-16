package org.mascota.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.data.local.MascotaSharedPreference.getUserId
import org.mascota.data.remote.model.request.content.ReqContent
import org.mascota.data.remote.model.response.content.*
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

    private val _resPart2ContentList = MutableLiveData<ResPart2ContentList>()
    val resPart2ContentList: LiveData<ResPart2ContentList>
        get() = _resPart2ContentList

    private var _resTableContent = listOf<ResContentList.Data.TableContent>()
    val resTableContent: List<ResContentList.Data.TableContent>
    get() = _resTableContent

    private val _resPart2ContentDetail = MutableLiveData<ResPart2ContentDetail>()
    val resPart2ContentDetail: LiveData<ResPart2ContentDetail>
        get() = _resPart2ContentDetail

    private lateinit var _chapterTitle : String


    fun postChapterTitle(chapterTitle: String) {
        _chapterTitle = chapterTitle
    }

    private lateinit var _chapterId : String

    fun postChapterId(chapterId: String) {
        _chapterId = chapterId
    }

    fun deleteContent() = viewModelScope.launch {
        runCatching { contentRepository.deleteContent(_chapterId) }
            .onSuccess {
                getResContentList()
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    // Part2 - delete
    fun deleteContent2() = viewModelScope.launch {
        kotlin.runCatching { contentRepository.deleteContentPart2(_chapterId) }
            .onSuccess {
                getResPart2ContentList()
            }
            .onFailure {
                it.printStackTrace()
            }

    }

    fun putContentEdit() = viewModelScope.launch {
        runCatching {
            contentRepository.putContentEdit(
                _chapterId,
                ReqContent(requireNotNull(_chapterTitle))
            )
        }
            .onSuccess {
                getResContentList()
            }
            .onFailure {
                it.printStackTrace()
            }
    }
    // Part2 - Edit
    fun putContentEdit2() = viewModelScope.launch {
        runCatching{
            contentRepository.putContentEditPart2(
                _chapterId,
                ReqContent(requireNotNull(_chapterTitle))
            )
        }
            .onSuccess {
                getResPart2ContentList()
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    //Part2 - Add
    fun putContentAdd2() = viewModelScope.launch {
        runCatching {
            contentRepository.postContentAddPart2(
                ReqContent(_chapterTitle,)
            )
        }
            .onSuccess { resContentAdd->
                Log.d("resContentAdd", resContentAdd.toString())
                getResPart2ContentList()

            }
            .onFailure {
                it.printStackTrace()
            }
    }


    fun postContentAdd() = viewModelScope.launch {
        runCatching {
            contentRepository.postContentAdd(
                getUserId(),
                ReqContent(_chapterTitle)
            )
        }
            .onSuccess { resContentAdd ->
                Log.d("resContentAdd", resContentAdd.toString())
                _resTableContent = resContentAdd.data.tableContents.map{ changeTableContent(it)}

            }
            .onFailure {
                it.printStackTrace()
            }
    }

    private fun changeTableContent(table: ResContentAdd.Data.TableContent) : ResContentList.Data.TableContent{
        return ResContentList.Data.TableContent(table.chapterId, table.chapter, table.chapterTitle?: "서버타이틀")
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

    fun getResPart2ContentList() = viewModelScope.launch {
        runCatching { contentRepository.getContentListPart2() }
            .onSuccess {
                Log.d("server", it.toString())
                _resPart2ContentList.postValue(it)
            }
            .onFailure {
                Log.d("server", "failure")
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

    fun getPart2ContentDetail(chapterId: String) = viewModelScope.launch {
        runCatching { contentRepository.getContentDetailPart2(chapterId) }
            .onSuccess {
                _resPart2ContentDetail.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }
}