package org.mascota.data.remote.model.response.content

data class ResContentDelete(
    // 목차 삭제
    val status: Int,
    val message: String,
    val data: Data
) {
    class Data(
    )
}