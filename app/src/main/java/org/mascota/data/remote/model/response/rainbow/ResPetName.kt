package org.mascota.data.remote.model.response.rainbow

data class ResPetName(
    val status: Int,
    val success: Boolean,
    val message: String,
    val `data`: Data
) {
    data class Data(
        val name: String
    )
}