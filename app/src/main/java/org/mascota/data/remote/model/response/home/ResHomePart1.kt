package org.mascota.data.remote.model.response.home

data class ResHomePart1(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val firstPartMainPage: FirstPartMainPage
    ) {
        data class FirstPartMainPage(
            val title: String,
            val bookImg: String,
            val diary: Diary,
            val tableContents: List<TableContent>,
            val secondPartBook: SecondPartBook
        ) {
            data class Diary(
                val chapter: Int,
                val episode: Int,
                val _id: String,
                val title: String,
                val contents: String,
                val date: String
            )

            data class TableContent(
                val chapterId: String,
                val chapter: Int,
                val chapterTitle: String,
                val episodePerchapterCount: Int
            )

            data class SecondPartBook(
                val userId: String,
                val imgs: String,
                val author: String,
                val date: String
            )
        }
    }
}