package org.mascota.data.remote.model.response.profile

data class ResRegisterPet(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val petId: List<String>
    )
}