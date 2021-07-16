package org.mascota.data.repository.profile

import org.mascota.data.remote.datasource.profile.ProfileDataSource
import org.mascota.data.remote.model.request.profile.ReqRegisterBook
import org.mascota.data.remote.model.request.profile.ReqRegisterPet
import org.mascota.data.remote.model.response.profile.ResRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterPet

class ProfileRepositoryImpl(private val remoteDataSource: ProfileDataSource) : ProfileRepository {
    override suspend fun postRegisterPet(
        body: ReqRegisterPet
    ): ResRegisterPet =
        remoteDataSource.postRegisterPet(body)

    override suspend fun postRegisterBook(path : String, body: ReqRegisterBook): ResRegisterBook =
        remoteDataSource.postRegisterBook(path, body)
}