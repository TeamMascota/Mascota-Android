package org.mascota.data.remote.api

import org.mascota.data.remote.model.request.rainbow.ReqRainbowEpilogue
import org.mascota.data.remote.model.response.rainbow.*
import retrofit2.http.*

interface RainbowService {
    //GET Rainbow Home
    @GET("rainbow/main/{userId}/{petId}")
    suspend fun getRainbowHome(
        @Path("userId") userId: String,
        @Path("petId") petId: String
    ): ResRainbowHome

    //GET Rainbow Pet Select
    @GET("rainbow/pet")
    suspend fun getFarewellSelect(): ResFarewellSelect

    //PUT Rainbow Content
    @PUT("rainbow/pet/{petId}")
    suspend fun putRainbowContent(
        @Path("petId") petId: String
    ): ResRainbowContent

    //DELETE Rainbow Quit
    @DELETE("rainbow/pet/{petId}")
    suspend fun deleteFarewellQuit(
        @Path("petId") petId: String
    ): ResFarewellQuit

    //GET Rainbow Book
    @GET("rainbow/record/{petId}")
    suspend fun getRainbowBook(
        @Path("userId") userId: String,
        @Path("petId") petId: String
    ): ResRainbowBook

    //GET Rainbow Best Moment
    @GET("rainbow/moment/{userId}/{petId}")
    suspend fun getRainbowBestMoment(
        @Path("userId") userId: String,
        @Path("petId") petId: String
    ): ResBestMoment

    //GET Rainbow Pet Name
    @GET("rainbow/parting/pet/{petId}")
    suspend fun getRainbowPetName(
        @Path("petId") petId: String
    ): ResPetName

    //POST Rainbow Epilogue
    @POST("rainbow/epilogue/{userId}/{petId}")
    suspend fun postRainbowEpilogue(
        @Path("userId") userId: String,
        @Path("petId") petId: String,
        @Body body: ReqRainbowEpilogue
    ): ResRainbowEpilogue



    //GET Sub Home
    @GET("rainbow/moment/sub/{petId}")
    suspend fun getRainbowSubHome(
        @Path("userId") userId: String,
        @Path("petId") petId: String
    ): ResRainbowSubHome
}