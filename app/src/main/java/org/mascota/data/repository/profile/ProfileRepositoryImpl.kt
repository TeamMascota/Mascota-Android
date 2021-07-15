package org.mascota.data.repository.profile

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.mascota.data.remote.datasource.profile.ProfileDataSource
import org.mascota.data.remote.model.request.profile.ReqRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterPet
import org.mascota.ui.view.profile.data.model.Pets

class ProfileRepositoryImpl(private val remoteDataSource: ProfileDataSource) : ProfileRepository {
    override suspend fun postRegisterPet(
        body: HashMap<String, RequestBody>,
        images: List<MultipartBody.Part?>
    ): ResRegisterPet =
        remoteDataSource.postRegisterPet(body, images)

    override suspend fun postRegisterBook(body: ReqRegisterBook): ResRegisterBook =
        remoteDataSource.postRegisterBook(body)
}