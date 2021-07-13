package org.mascota.data.remote.datasource.user

import org.mascota.data.remote.api.UserService
import org.mascota.data.remote.model.request.user.ReqSignIn
import org.mascota.data.remote.model.request.user.ReqSignUp
import org.mascota.data.remote.model.response.user.ResSignIn
import org.mascota.data.remote.model.response.user.ResSignUp

class RemoteUserDataSource(private val service: UserService) : UserDataSource {
    override suspend fun postSignUp(body: ReqSignUp): ResSignUp = service.postSignUp(body)

    override suspend fun postSignIn(body: ReqSignIn): ResSignIn = service.postSignIn(body)
}