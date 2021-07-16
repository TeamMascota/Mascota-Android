package org.mascota.data.remote.model.response.rainbow

data class ResFarewellSelect(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val pet: List<Pet>
    ){
        data class Pet(
            val _id: String,
            val name: String,
            val img: String
        )
    }
}