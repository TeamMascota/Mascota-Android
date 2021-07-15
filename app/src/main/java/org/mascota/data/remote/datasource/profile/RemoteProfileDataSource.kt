package org.mascota.data.remote.datasource.profile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.mascota.data.remote.api.ProfileService
import org.mascota.data.remote.model.request.profile.ReqRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterPet

class RemoteProfileDataSource(private val service: ProfileService) : ProfileDataSource {
    override suspend fun postRegisterPet(
        body: HashMap<String, RequestBody>,
        images: List<MultipartBody.Part?>
    ): ResRegisterPet =
        service.postRegisterPet(body, images)

    override suspend fun postRegisterBook(body: ReqRegisterBook): ResRegisterBook =
        service.postRegisterBook(body)
}