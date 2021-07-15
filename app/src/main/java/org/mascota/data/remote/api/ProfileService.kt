package org.mascota.data.remote.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.mascota.data.remote.model.request.profile.ReqRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterPet
import org.mascota.ui.view.profile.data.model.Pets
import retrofit2.http.*

interface ProfileService {
    //POST 반려동물 등록
    @Multipart
    @JvmSuppressWildcards
    @POST("pet/register")
    suspend fun postRegisterPet(
        @PartMap body: HashMap<String, RequestBody>,
        @Part images: List<MultipartBody.Part?>
    ): ResRegisterPet

    //POST 책 등록
    @POST("book/prologue")
    suspend fun postRegisterBook(
        @Body body: ReqRegisterBook
    ): ResRegisterBook
}