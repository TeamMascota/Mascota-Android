package org.mascota.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.mascota.ui.view.calendar.data.datasource.AuthorDataSource
import org.mascota.ui.view.calendar.data.model.AuthorInfoData

class CalendarViewModel(private val authorDataSource: AuthorDataSource) : ViewModel() {
    private val _authorInfo = MutableLiveData<AuthorInfoData>()
    val authorInfo: LiveData<AuthorInfoData>
        get() = _authorInfo

    fun getAuthorInfo() = viewModelScope.launch {
        runCatching { authorDataSource.getAuthorInfoData() }
            .onSuccess {
                _authorInfo.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }

}