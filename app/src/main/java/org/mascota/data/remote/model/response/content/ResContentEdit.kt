package org.mascota.data.remote.model.response.content

data class ResContentEdit(
    // 목차 수정
    val status: Int,
    val message: String,
    val data: Data
) {
    class Data(
    )
}