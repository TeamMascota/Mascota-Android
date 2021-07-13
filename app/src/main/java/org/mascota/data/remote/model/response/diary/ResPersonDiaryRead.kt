package org.mascota.data.remote.model.response.diary

import com.google.gson.annotations.SerializedName

data class ResPersonDiaryRead(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val secondPartDiary: SecondPartDiary
    ) {
        data class SecondPartDiary(
            val _id: String,
            val episode: Int,
            val title: String,
            val diaryImg: List<String>,
            val date: String,
            val contents: String,
            val feelingList: List<Feeling>
        ) {
            data class Feeling(
                val kind: Int,
                @SerializedName("petImgs")
                val petImg: String,
                val feeling: Int
            )
        }
    }
}