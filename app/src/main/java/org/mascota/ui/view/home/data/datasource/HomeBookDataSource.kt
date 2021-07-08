package org.mascota.ui.view.home.data.datasource

import org.mascota.ui.view.home.data.model.HomeBookInfoData
import org.mascota.ui.view.home.data.model.HomeContentInfoData
import org.mascota.ui.view.home.data.model.HomeDiaryInfoData

interface HomeBookDataSource {
    fun getHomeBookInfoData(): HomeBookInfoData
    fun getHomeDiaryInfoData(): HomeDiaryInfoData
    fun getHomeContentInfoData(): List<HomeContentInfoData>
}