package org.mascota.ui.view.content.detail.data.datasource

import org.mascota.ui.view.content.detail.data.model.ContentDiaryInfoData
import org.mascota.ui.view.content.detail.data.model.ContentMonthInfoData

class LocalContentDetailDataSource : ContentDetailDataSource {
    override fun getContentDiaryInfoData(): List<ContentDiaryInfoData> =
        listOf<ContentDiaryInfoData>(
            ContentDiaryInfoData(
                "13일",
                "금요일",
                1,
                "코봉이의 중성화날",
                "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라",
                "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg"
            ),
            ContentDiaryInfoData(
                "13일",
                "금요일",
                1,
                "코봉이의 중성화날",
                "가나다라마바사아자차카타파하가나다라마바사아자차카타",
                "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg"
            ),
            ContentDiaryInfoData(
                "13일",
                "금요일",
                1,
                "코봉이의 중성화날",
                "가나다라마바사아자차카타파하가나다라마바사아자차카타",
                "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg"
            ),
            ContentDiaryInfoData(
                "13일",
                "금요일",
                1,
                "코봉이의 중성화날",
                "가나다라마바사아자차카타파하가나다라마바사아자차카타",
                "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg"
            )
        )

    override fun getContentMonthInfoData(): List<ContentMonthInfoData> =
        listOf<ContentMonthInfoData>(
            ContentMonthInfoData(7, 15, getContentDiaryInfoData().toMutableList()),
            ContentMonthInfoData(6, 3, getContentDiaryInfoData().toMutableList())
        )
}