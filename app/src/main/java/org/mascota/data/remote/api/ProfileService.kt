package org.mascota.data.remote.api

import okhttp3.MultipartBody
import org.mascota.data.remote.model.request.profile.ReqRegisterBook
import org.mascota.data.remote.model.request.profile.ReqRegisterPet
import org.mascota.data.remote.model.response.profile.ResRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterPet
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ProfileService {
    //POST 반려동물 등록
    @Multipart
    @POST("pet/register")
    suspend fun postRegisterPet(
        @Body body: ReqRegisterPet,
        @Part images : List<MultipartBody.Part>
    ): ResRegisterPet

    //POST 책 등록
    @Multipart
    @POST("book/prologue")
    suspend fun postRegisterBook(
        @Body body: ReqRegisterBook
    ): ResRegisterBook
}