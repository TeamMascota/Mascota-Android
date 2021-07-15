package org.mascota.data.remote.model.response.content

import com.google.gson.annotations.SerializedName

data class ResContentAdd(
    // 목차 추가
    val status: Int,
    val message: String,
    val data: Data
) {
    data class Data (
        val tableContents: List<TableContent>
    ){
        data class TableContent(
            val chapterId: String,
            val chapter: Int,
            val chapterTitle: String,
            @SerializedName("episodePerchapterCount")
            val episodePerChapterCount: String
        )
    }
}