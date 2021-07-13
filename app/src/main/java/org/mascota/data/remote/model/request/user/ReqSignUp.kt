package org.mascota.data.remote.model.request.user

data class ReqSignUp(
    // 회원가입
    val email: String,
    val password: String
)