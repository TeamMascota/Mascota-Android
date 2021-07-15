package org.mascota.ui.view.profile.data.model

import com.google.gson.annotations.SerializedName
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


data class Pets(
    @SerializedName("name")
    val name: String,
    @SerializedName("kind")
    val kind: Int,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("gender")
    val gender: Int
) {
    fun toRequestBody(): Pets {
        return Pets(name, kind, startDate, gender)
    }
}