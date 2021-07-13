package org.mascota.data.remote.api

import org.mascota.data.remote.model.request.user.ReqSignIn
import org.mascota.data.remote.model.request.user.ReqSignUp
import org.mascota.data.remote.model.response.user.ResSignIn
import org.mascota.data.remote.model.response.user.ResSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    //POST	회원가입
    @POST("user/register")
    suspend fun postSignUp(
        @Body body: ReqSignUp
    ): ResSignUp

    //POST	로그인
    @POST("user/login")
    suspend fun postSignIn(
        @Body body: ReqSignIn
    ): ResSignIn
}