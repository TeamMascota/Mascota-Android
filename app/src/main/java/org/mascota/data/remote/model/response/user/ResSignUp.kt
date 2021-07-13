package org.mascota.data.remote.model.response.user

data class ResSignUp(
    // 회원가입
    val status: Int,
    val message: String,
    val data: Data
) {
    data class Data(
        val bookId: String
    )
}