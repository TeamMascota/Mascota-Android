package org.mascota.data.remote.model.response.diary

data class ResDiaryDelete(
    // 일기 삭제
    val status: Int,
    val message: String,
    val data: Data
) {
    class Data(
    )
}