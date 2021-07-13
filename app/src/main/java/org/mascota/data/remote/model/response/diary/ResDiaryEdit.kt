package org.mascota.data.remote.model.response.diary

data class ResDiaryEdit(
    // 일기 수정
    val status: Int,
    val message: String,
    val data: Data
) {
    class Data(
    )
}