package org.mascota.data.remote.model.response.content

data class ResContentAdd(
    // 목차 추가
    val status: Int,
    val message: String,
    val data: Data
) {
    class Data(
    )
}