package org.mascota.data.remote.model.request.profile

data class ReqRegisterBook(
    // 책 등록
    val image: String,
    val title: String,
    val userName: String,
    val prologueTitle: String,
    val prologueContents: String
)