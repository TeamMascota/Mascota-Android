package org.mascota.ui.view.home.data.datasource

import org.mascota.ui.view.home.data.model.HomeBookInfoData
import org.mascota.ui.view.home.data.model.HomeContentInfoData

class LocalHomeBookDataSource : HomeBookDataSource {
    override fun getHomeBookInfoData(): HomeBookInfoData = HomeBookInfoData("1부 ", "모여봐요 코봉의 숲", "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg")
    override fun getHomeContentInfoData(): List<HomeContentInfoData> = listOf<HomeContentInfoData> (
        HomeContentInfoData(1, "코봉의 봄", 30),
        HomeContentInfoData(2, "코봉의 여름", 15),
        HomeContentInfoData(3, "코봉의 가을", 15),
        HomeContentInfoData(4, "코봉의 겨울", 15)
    )
}