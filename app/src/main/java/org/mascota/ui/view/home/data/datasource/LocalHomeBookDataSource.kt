package org.mascota.ui.view.home.data.datasource

import org.mascota.ui.view.home.data.model.HomeBookInfoData
import org.mascota.ui.view.home.data.model.HomeDiaryInfoData
import org.mascota.ui.view.home.data.model.HomePageInfoData

class LocalHomeBookDataSource : HomeBookDataSource {
    override fun getHomeBookInfoData(): HomeBookInfoData = HomeBookInfoData("1부 ", "모여봐요 코봉의 숲", "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg")
    override fun getHomeDiaryInfoData(): HomeDiaryInfoData = HomeDiaryInfoData("162화", "제목", "내용", "2020.02.20")
    override fun getHomePageInfoData(): HomePageInfoData = HomePageInfoData(prolog = true)
}