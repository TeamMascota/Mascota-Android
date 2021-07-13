package org.mascota.data.remote.model.request.user

data class ReqSignIn(
    // 로그인
    val email: String,
    val password: String
)