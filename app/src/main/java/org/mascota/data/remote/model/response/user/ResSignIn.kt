package org.mascota.data.remote.model.response.user

data class ResSignIn(
    // 로그인
    val status: Int,
    val message: String,
    val data: Data
) {
    class Data(
        val userId : String,
        val petId : String
    )
}