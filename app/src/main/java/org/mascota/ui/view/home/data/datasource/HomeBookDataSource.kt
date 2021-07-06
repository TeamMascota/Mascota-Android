package org.mascota.ui.view.home.data.datasource

import org.mascota.ui.view.home.data.model.HomeBookInfoData
import org.mascota.ui.view.home.data.model.HomeContentInfoData

interface HomeBookDataSource {
    fun getHomeBookInfoData() : HomeBookInfoData
    fun getHomeContentInfoData() : List<HomeContentInfoData>
}