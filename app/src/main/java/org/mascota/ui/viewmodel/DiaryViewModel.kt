package org.mascota.ui.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.mascota.data.local.MascotaSharedPreference.getUserId
import org.mascota.data.remote.model.request.diary.ReqPetDiaryWrite
import org.mascota.data.remote.model.response.content.ResContentList
import org.mascota.data.remote.model.response.diary.ResDiaryRead
import org.mascota.data.remote.model.response.diary.ResPersonDiaryRead
import org.mascota.data.remote.model.response.diary.ResPetInfo
import org.mascota.data.repository.content.ContentRepository
import org.mascota.data.repository.diary.DiaryRepository
import org.mascota.util.CalendarUtil.convertCalendarToBeFamilyDateString
import org.mascota.util.Event
import java.util.*

class DiaryViewModel(
    private val diaryRepository: DiaryRepository,
    private val contentRepository: ContentRepository
) : ViewModel() {
    var title = ""
    var contents = ""
    var date = convertCalendarToBeFamilyDateString(Calendar.getInstance(Locale.KOREA))
    val emptyEmotionData = ReqPetDiaryWrite.Character("", -1)
    val emotionList = MutableList(4) { emptyEmotionData }

    private val _imageList = mutableListOf<MultipartBody.Part?>()

    val imageUriList = MutableList<Uri?>(5) { null }

    private val _contentList = MutableLiveData<List<ResContentList.Data.TableContent>>()
    val contentList: LiveData<List<ResContentList.Data.TableContent>>
        get() = _contentList


    private val _selectedEmotion = MutableLiveData<Int>()
    val selectedEmotion: LiveData<Int>
        get() = _selectedEmotion

    private val _petInfo = MutableLiveData<ResPetInfo>()
    val petInfo: LiveData<ResPetInfo>
        get() = _petInfo

    private val _nextBtnEnableEvent = MutableLiveData<Event<Boolean>>()
    val nextBtnEnableEvent: LiveData<Event<Boolean>>
        get() = _nextBtnEnableEvent

    private val _diaryReadPet = MutableLiveData<ResDiaryRead>()
    val diaryReadPet: LiveData<ResDiaryRead>
        get() = _diaryReadPet

    private val _diaryReadPerson = MutableLiveData<ResPersonDiaryRead>()
    val diaryReadPerson: LiveData<ResPersonDiaryRead>
        get() = _diaryReadPerson

    private lateinit var _petDiaryId: String

    fun postPetDiaryId(petDiaryId: String) {
        _petDiaryId = petDiaryId
    }

    fun getPetDiaryRead() = viewModelScope.launch {
        runCatching { diaryRepository.getPetDiaryRead(_petDiaryId) }
            .onSuccess {
                _diaryReadPet.postValue(it)
                Log.d("server-diary-read", it.toString())
            }
            .onFailure {
                it.printStackTrace()
                Log.d("server-diary-read-fail", "why?????????")
            }
    }

    fun getPersonDiaryRead() = viewModelScope.launch {
        runCatching { diaryRepository.getPersonDiaryRead(_petDiaryId) }
            .onSuccess {
                _diaryReadPerson.postValue(it)
                Log.d("server-person-diary", it.toString())
            }
            .onFailure {
                Log.d("server-person-diary", "chapter2fail")
                it.printStackTrace()
            }
    }

    fun postBtnEnable(isEnable: Boolean) =
        viewModelScope.launch { _nextBtnEnableEvent.postValue(Event(isEnable)) }

    fun postSelectedEmotion(emotion: Int) =
        viewModelScope.launch { _selectedEmotion.postValue(emotion) }

    fun getPetInfo() {
        viewModelScope.launch {
            runCatching { diaryRepository.getAnimalInfo() }
                .onSuccess {
                    Log.d("dfdfdf", it.toString())
                    _petInfo.postValue(it)
                }
                .onFailure {
                    it.printStackTrace()
                }
        }
    }

    fun getContentPart1() {
        viewModelScope.launch {
            runCatching { contentRepository.getContentList(getUserId()) }
                .onSuccess {
                    _contentList.postValue(it.data.tableContents)
                }
                .onFailure {
                    it.printStackTrace()
                }
        }
    }

    fun postDiary() {
        viewModelScope.launch {
            runCatching {
                diaryRepository.postPetDiaryWrite(
                    ReqPetDiaryWrite(
                        emotionList.filter {
                            it.feeling != -1
                        },
                        title, listOf(""), contents, date, getUserId()
                    )
                )
            }
                .onSuccess {

                }
                .onFailure {
                    it.printStackTrace()
                }
        }
    }

    fun getContentPart2() {
        viewModelScope.launch {
            runCatching { contentRepository.getContentListPart2() }
                .onSuccess {
                    //_contentList.postValue(it.data.tableContents)
                }
                .onFailure {
                    it.printStackTrace()
                }
        }
    }


    fun addImageList(image: MultipartBody.Part?) {
        _imageList.add(image)
    }

    fun setImageList(index: Int, image: MultipartBody.Part?) {
        _imageList[index] = image
    }

}