package org.mascota.data.remote.model.response.content

data class ResContentList(
    // 목차 리스트
    val tableContents: List<TableContent>
) {
    data class TableContent(
        val chapterId: String,
        val chapter: Int,
        val chapterTitle: String
    )
}