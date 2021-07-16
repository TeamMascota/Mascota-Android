package org.mascota.data.remote.model.response.content

import com.google.gson.annotations.SerializedName

data class ResPart2ContentDetail(
    // 2부 목차별 일기 조회
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        @SerializedName("_id")
        val chapterId: String,
        val chapter: Int,
        val chapterTitle: String,
        @SerializedName("diariesOfMonth")
        val diaryiesOfMonth: List<Monthly>
    ) {
        data class Monthly(
            val month: Int,
            @SerializedName("diaryCountOfTableContents")
            val diaryCountOfTableContents: Int,
            @SerializedName("diaries")
            val diaries: List<Diary>
        ) {
            data class Diary(
                val diaryId: String,
                val days: Int,
                val dayOfWeek: String,
                val feeling: Int,
                val kind: Int,
                val title: String,
                val contents: String,
                val img: String,
            )
        }
    }
}

