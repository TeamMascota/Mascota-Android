package org.mascota.ui.view.home.data.datasource

import org.mascota.ui.view.home.data.model.HomeBookInfoData
import org.mascota.ui.view.home.data.model.HomeDiaryInfoData
import org.mascota.ui.view.home.data.model.HomePageInfoData

interface HomeBookDataSource {
    fun getHomeBookInfoData() : HomeBookInfoData
    fun getHomeDiaryInfoData() : HomeDiaryInfoData
    fun getHomePageInfoData() : HomePageInfoData
}