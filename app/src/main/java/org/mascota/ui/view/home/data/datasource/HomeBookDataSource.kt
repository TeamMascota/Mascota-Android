package org.mascota.ui.view.home.data.datasource

import org.mascota.ui.view.home.data.model.HomeBookInfoData

interface HomeBookDataSource {
    fun getHomeBookInfoData() : HomeBookInfoData
}