package org.mascota.data.repository.profile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.mascota.data.remote.model.request.profile.ReqRegisterBook
import org.mascota.data.remote.model.request.profile.ReqRegisterPet
import org.mascota.data.remote.model.response.profile.ResRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterPet

interface ProfileRepository {
    //POST 반려동물 등록
    suspend fun postRegisterPet(body: ReqRegisterPet, images: RequestBody): ResRegisterPet

    //POST 책 등록
    suspend fun postRegisterBook(body: ReqRegisterBook): ResRegisterBook
}