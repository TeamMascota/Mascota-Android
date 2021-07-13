package org.mascota.data.remote.model.response.diary

data class ResPetDiaryWrite(
    // 반려동물 일기 작성
    val status: Int,
    val message: String,
    val data: Data
) {
    class Data(
    )
}