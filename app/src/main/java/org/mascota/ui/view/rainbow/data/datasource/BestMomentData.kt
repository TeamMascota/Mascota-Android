package org.mascota.ui.view.rainbow.data.datasource

data class BestMomentData(
    val theBestMoments: TheBestMoment.Diary
) {
    data class TheBestMoment(
        val comment: String,
        val feeling: Int,
        val diaries: List<Diary>
    ) {
        data class Diary(
            val chapter: Int,
            val episode: Int,
            val title: String,
            val contents: String,
            val date: String
        )
    }
}