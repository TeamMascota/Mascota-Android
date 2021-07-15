package org.mascota.data.remote.model.response.content

import com.google.gson.annotations.SerializedName

data class ResPart2ContentList(
    // 목차 리스트
    val status : Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data (
        val tableContents: List<TableContent>
    ){
        data class TableContent(
            @SerializedName("_id")
            val chapterId: String,
            @SerializedName("chapter")
            val chapter: Int,
            @SerializedName("title")
            val chapterTitle: String
        )
    }
}