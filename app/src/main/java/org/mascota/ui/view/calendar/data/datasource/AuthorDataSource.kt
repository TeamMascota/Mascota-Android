package org.mascota.ui.view.calendar.data.datasource

import org.mascota.ui.view.calendar.data.model.AuthorInfoData

interface AuthorDataSource {
    fun getAuthorInfoData() : AuthorInfoData
}