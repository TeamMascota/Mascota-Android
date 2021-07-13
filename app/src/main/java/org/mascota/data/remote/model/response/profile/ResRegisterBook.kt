package org.mascota.data.remote.model.response.profile

data class ResRegisterBook(
    // 책 등록
    val status: Int,
    val message: String,
    val data: Data
) {
    class Data(
    )
}