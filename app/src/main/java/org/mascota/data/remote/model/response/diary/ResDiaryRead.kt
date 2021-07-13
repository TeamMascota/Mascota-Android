package org.mascota.data.remote.model.response.diary

import com.google.gson.annotations.SerializedName

data class ResDiaryRead(
    // 일기 조회
    val status: Int,
    val success: Boolean,
    val data: Data
) {
    data class Data(
        val petDiary: PetDiary
    ) {
        data class PetDiary(
            val _id: String,
            val episode: Int,
            val title: String,
            val bookImg: List<String>,
            val date: String,
            val contents: String,
            val timeTogether: Int,
            val feelingList: List<Feeling>
        ) {
            data class Feeling(
                val feeling: Int,
                val kind: Int,
                @SerializedName("petImgs")
                val petImg: String
            )
        }
    }
}