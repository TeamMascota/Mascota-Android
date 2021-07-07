package org.mascota.ui.view.content.edit.data.datasource

import org.mascota.ui.view.content.edit.data.model.ContentEditInfoData

class LocalContentEditDataSource : ContentEditDataSource {
    override fun getContentEditInfoData(): List<ContentEditInfoData> = listOf<ContentEditInfoData>(
        ContentEditInfoData(1, "코봉의 봄"),
        ContentEditInfoData(2, "코봉의 봄"),
        ContentEditInfoData(3, "코봉의 봄"),
        ContentEditInfoData(4, "코봉의 봄")
    )
}