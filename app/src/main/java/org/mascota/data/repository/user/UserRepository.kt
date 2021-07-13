package org.mascota.data.repository.user

import org.mascota.data.remote.model.request.user.ReqSignIn
import org.mascota.data.remote.model.request.user.ReqSignUp
import org.mascota.data.remote.model.response.user.ResSignIn
import org.mascota.data.remote.model.response.user.ResSignUp

interface UserRepository {
    //POST	회원가입
    suspend fun postSignUp(body: ReqSignUp): ResSignUp

    //POST	로그인
    suspend fun postSignIn(body : ReqSignIn): ResSignIn
}