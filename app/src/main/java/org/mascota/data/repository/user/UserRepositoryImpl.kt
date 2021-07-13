package org.mascota.data.repository.user

import org.mascota.data.remote.datasource.user.UserDataSource
import org.mascota.data.remote.model.request.user.ReqSignIn
import org.mascota.data.remote.model.request.user.ReqSignUp
import org.mascota.data.remote.model.response.user.ResSignIn
import org.mascota.data.remote.model.response.user.ResSignUp

class UserRepositoryImpl(private val remoteDataSource : UserDataSource) : UserRepository {
    override suspend fun postSignUp(body: ReqSignUp): ResSignUp = remoteDataSource.postSignUp(body)

    override suspend fun postSignIn(body: ReqSignIn): ResSignIn = remoteDataSource.postSignIn(body)
}