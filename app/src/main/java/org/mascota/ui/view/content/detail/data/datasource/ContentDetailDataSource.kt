package org.mascota.ui.view.content.detail.data.datasource

import org.mascota.ui.view.content.detail.data.model.ContentDiaryInfoData
import org.mascota.ui.view.content.detail.data.model.ContentMonthInfoData

interface ContentDetailDataSource {
    fun getContentDiaryInfoData(): List<ContentDiaryInfoData>
    fun getContentMonthInfoData(): List<ContentMonthInfoData>
}