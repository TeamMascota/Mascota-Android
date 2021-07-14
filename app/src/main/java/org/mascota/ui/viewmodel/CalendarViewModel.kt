package org.mascota.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.data.local.MascotaSharedPreference.getPart
import org.mascota.data.remote.model.response.calendar.ResCalendar
import org.mascota.data.repository.calendar.CalendarRepository
import org.mascota.ui.view.calendar.data.datasource.AuthorDataSource
import org.mascota.ui.view.calendar.data.model.AuthorInfoData
import org.mascota.util.CalendarUtil.initCalendar
import java.util.*

class CalendarViewModel(
    private val calendarRepository: CalendarRepository,
    private val authorDataSource: AuthorDataSource
) : ViewModel() {
    var nowCalendar = initCalendar(Calendar.getInstance(Locale.KOREA))
    private val _authorInfo = MutableLiveData<AuthorInfoData>()
    val authorInfo: LiveData<AuthorInfoData>
        get() = _authorInfo

    private val _dateItem = MutableLiveData<ResCalendar>()
    val dateItem: LiveData<ResCalendar>
        get() = _dateItem

    private val _curCalendar = MutableLiveData(nowCalendar)
    val curCalendar: LiveData<Calendar>
        get() = _curCalendar

    fun getAuthorInfo() = viewModelScope.launch {
        runCatching { authorDataSource.getAuthorInfoData() }
            .onSuccess {
                Log.d("tets", "fsdasdfa")
                _authorInfo.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

    fun getDateItem(calendar: Calendar) = viewModelScope.launch {
        runCatching {
            with(calendar) {
                calendarRepository.getCalendar(
                    get(Calendar.YEAR),
                    get(Calendar.MONTH) + 1,
                    getPart()
                )
            }
        }.onSuccess {
            _dateItem.postValue(it)
        }.onFailure { it.printStackTrace() }
    }

    fun setCalendar() {
        _curCalendar.postValue(nowCalendar)
    }

    fun addMonth() {
        nowCalendar.add(Calendar.MONTH, NEXT)
        setCalendar()
    }

    fun minusMonth() {
        nowCalendar.add(Calendar.MONTH, PREV)
        setCalendar()
    }

    fun addYear() {
        nowCalendar.add(Calendar.YEAR, NEXT)
        setCalendar()
    }

    fun minusYear() {
        nowCalendar.add(Calendar.YEAR, PREV)
        setCalendar()
    }

    companion object {
        const val PREV = -1
        const val NEXT = 1
    }
}