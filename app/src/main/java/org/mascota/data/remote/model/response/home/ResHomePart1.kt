package org.mascota.data.remote.model.response.home

data class ResHomePart1(
    //홈 파트2
    val title: String,
    val bookImage: String,
    val diary: Diary,
    val tableContents: List<TableContent>,
    val secondPartBook: SecondPartBook
) {
    data class Diary(
        val _id: String,
        val chapter: Int,
        val episode: Int,
        val title: String,
        val contents: String,
        val date: String
    )

    data class TableContent(
        val _id: String,
        val chapter: Int,
        val chapterName: String,
        val episodePerchapterCount: Int
    )

    data class SecondPartBook(
        val userId: String,
        val imgs: String,
        val author: String,
        val date: String
    )
}