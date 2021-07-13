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
        val memory: Memory,
        val tableContents: List<TableContent>,
        val firstPartBook: FirstPartBook
    ) {
        data class Memory(
            val diary: Diary,
            val nextEpisode: Int
        ) {
            data class Diary(
                val episode: Int,
                val title: String,
                val contents: String,
                val date: String
            )
        }

        data class TableContent(
            val chapter: Int,
            val title: String,
            val episodePerChapterCount: Int,
            val _id: String
        )

        data class FirstPartBook(
            val _id: String,
            val bookImg: String,
            val author: String,
            val date: String
        )
    }
}