package org.mascota.ui.view.home.data.datasource

import org.mascota.ui.view.home.data.model.HomeBookInfoData
import org.mascota.ui.view.home.data.model.HomeDiaryInfoData
import org.mascota.ui.view.home.data.model.HomePageInfoData
import org.mascota.ui.view.home.data.model.HomeContentInfoData

interface HomeBookDataSource {
    fun getHomeBookInfoData() : HomeBookInfoData
    fun getHomeDiaryInfoData() : HomeDiaryInfoData
    fun getHomePageInfoData() : HomePageInfoData
    fun getHomeContentInfoData() : List<HomeContentInfoData>
}