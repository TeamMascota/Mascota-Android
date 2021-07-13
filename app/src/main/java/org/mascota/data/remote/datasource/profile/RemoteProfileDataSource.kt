package org.mascota.data.remote.datasource.profile

import org.mascota.data.remote.api.ProfileService
import org.mascota.data.remote.model.request.profile.ReqRegisterBook
import org.mascota.data.remote.model.request.profile.ReqRegisterPet
import org.mascota.data.remote.model.response.profile.ResRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterPet

class RemoteProfileDataSource(private val service: ProfileService) : ProfileDataSource {
    override suspend fun postRegisterPet(body: ReqRegisterPet): ResRegisterPet =
        service.postRegisterPet(body)

    override suspend fun postRegisterBook(body: ReqRegisterBook): ResRegisterBook =
        service.postRegisterBook(body)
}