package org.mascota.data.remote.model.request.profile

data class ReqRegisterPet(
    // 반려동물 등록
    val pets: List<Pet>,
    val userId: String
) {
    data class Pet(
        val name: String,
        val kind: Int,
        val startDate: String,
        val gender: Int
    )
}