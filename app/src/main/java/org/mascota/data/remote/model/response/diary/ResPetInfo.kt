package org.mascota.data.remote.model.response.diary

data class ResPetInfo(
    val status: Int,
    val message: String,
    val data: Data
) {
    data class Data(
        val pets: List<Pet>
    ) {
        data class Pet(
            val name: String,
            val image: String,
            val kind: Int,
            val petId: String
        )
    }
}