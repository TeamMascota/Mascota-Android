package org.mascota.data.remote.model.response.home

data class ResHomePart2(
    val status: Int,
    val message: String,
    val success: Boolean,
    val data: Data
) {
    data class Data(
        val part: Int,
        val author: String,
        val bookImg: String,
        val secondPartMainPage: SecondPartMainPage,
    ) {
        data class SecondPartMainPage(
            val title: String,
            val bookImg: String,
            val diary: Diary,
            val tableContents: List<TableContent>,
            val firstPartBook : FirstPartBook

            ) {
            data class Diary(
                val episode: Int,
                val title: String,
                val contents: String,
                val date: String,
                val _id: String,
                val chapter: Int
            )

            data class TableContent (
                val chapter: Int,
                val chapterTitle: String,
                val episodePerChapterCount: Int,
                val chapterId: String
                )

            data class FirstPartBook(
                val userid: String,
                val bookImg: String,
                val author: String,
                val date: String
            )
        }
    }
}