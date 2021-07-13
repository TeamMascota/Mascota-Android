package org.mascota.data.remote.datasource.user

import org.mascota.data.remote.model.request.user.ReqSignIn
import org.mascota.data.remote.model.request.user.ReqSignUp
import org.mascota.data.remote.model.response.user.ResSignIn
import org.mascota.data.remote.model.response.user.ResSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserDataSource {
    //POST	회원가입
    suspend fun postSignUp(body: ReqSignUp): ResSignUp

    //POST	로그인
    suspend fun postSignIn(body : ReqSignIn): ResSignIn
}