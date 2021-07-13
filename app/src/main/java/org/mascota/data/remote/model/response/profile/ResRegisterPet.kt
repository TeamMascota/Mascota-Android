package org.mascota.data.remote.model.response.profile

data class ResRegisterPet(
    // 반려동물 등록
    val status: Int,
    val message: String,
    val data: Data
) {
    class Data(
    )
}