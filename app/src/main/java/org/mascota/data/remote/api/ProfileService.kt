package org.mascota.data.remote.api

import org.mascota.data.remote.model.request.profile.ReqRegisterBook
import org.mascota.data.remote.model.request.profile.ReqRegisterPet
import org.mascota.data.remote.model.response.profile.ResRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterPet
import retrofit2.http.*

interface ProfileService {
    //POST 반려동물 등록
    @POST("pet/register")
    suspend fun postRegisterPet(
        @Body body: ReqRegisterPet
    ): ResRegisterPet

    //POST 책 등록
    @POST("book/prologue/{userId}")
    suspend fun postRegisterBook(
        @Path("userId") userId : String,
        @Body body: ReqRegisterBook
    ): ResRegisterBook
}