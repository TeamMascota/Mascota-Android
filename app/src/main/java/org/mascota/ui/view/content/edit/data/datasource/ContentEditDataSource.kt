package org.mascota.ui.view.content.edit.data.datasource

import org.mascota.ui.view.content.edit.data.model.ContentEditInfoData

interface ContentEditDataSource {
    fun getContentEditInfoData(): List<ContentEditInfoData>
}