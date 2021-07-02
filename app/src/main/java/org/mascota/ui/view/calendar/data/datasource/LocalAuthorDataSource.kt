package org.mascota.ui.view.calendar.data.datasource

import org.mascota.ui.view.calendar.data.model.AuthorInfoData

class LocalAuthorDataSource : AuthorDataSource {
    override fun getAuthorInfoData(): AuthorInfoData = AuthorInfoData("최소은", "코봉이", "162")
}