package org.mascota.data.remote.api

import org.mascota.data.remote.model.response.home.ResHomePart1
import org.mascota.data.remote.model.response.home.ResHomePart2
import org.mascota.data.remote.model.response.user.ResSignUp
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    //GET Home Part1
    @GET("firstPart/main/:{userId}")
    suspend fun getHomePart1(
        @Path("userId") userId: String
    ): ResHomePart1

    //GET Home Part2
    @GET("secondPart/main/:{userId}")
    suspend fun getHomePart2(
        @Path("userId") userId: String
    ): ResHomePart2
}