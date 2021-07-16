package org.mascota.data.remote.model.response.rainbow

data class ResFarewellQuit(
    // 무지개 이별준비 취소
    val status: Int,
    val success: Boolean,
    val message: String,
)