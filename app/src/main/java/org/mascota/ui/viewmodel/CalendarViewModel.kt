package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.ui.view.calendar.data.datasource.AuthorDataSource
import org.mascota.ui.view.calendar.data.model.AuthorInfoData
import org.mascota.util.CalendarUtil.initCalendar
import java.util.*

class CalendarViewModel(private val authorDataSource: AuthorDataSource) : ViewModel() {
    private val nowCalendar = initCalendar(Calendar.getInstance(Locale.KOREA))
    private val _authorInfo = MutableLiveData<AuthorInfoData>()
    val authorInfo: LiveData<AuthorInfoData>
        get() = _authorInfo

    private val _curCalendar = MutableLiveData<Calendar>()
    val curCalendar: LiveData<Calendar>
        get() = _curCalendar

    fun getAuthorInfo() = viewModelScope.launch {
        runCatching { authorDataSource.getAuthorInfoData() }
            .onSuccess {
                _authorInfo.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
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