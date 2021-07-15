package org.mascota.data.repository.profile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.mascota.data.remote.model.request.profile.ReqRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterPet
import org.mascota.ui.view.profile.data.model.Pets

interface ProfileRepository {
    //POST 반려동물 등록
    suspend fun postRegisterPet(
        body: HashMap<String, RequestBody>,
        images: List<MultipartBody.Part?>
    ): ResRegisterPet

    //POST 책 등록
    suspend fun postRegisterBook(body: ReqRegisterBook): ResRegisterBook
}