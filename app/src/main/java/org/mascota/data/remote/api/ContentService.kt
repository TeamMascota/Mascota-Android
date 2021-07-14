package org.mascota.data.remote.api

import org.mascota.data.remote.model.request.content.ReqContent
import org.mascota.data.remote.model.request.content.ReqContentDelete
import org.mascota.data.remote.model.response.content.*
import org.mascota.data.remote.model.response.diary.ResDiaryRead
import retrofit2.http.*

interface ContentService {
    //GET Content Detail
    @GET("chapter/pet/{chapterId}")
    suspend fun getContentDetail(
        @Path("chapterId") chapterId: String
    ): ResContentDetail

    //GET Content List
    @GET("chapter/chapter/{userId}")
    suspend fun getContentList(
        @Path("userId") userId: String
    ): ResContentList

    //POST Content Add
    @POST("chapter/{userId}")
    suspend fun postContentAdd(
        @Path("userId") userId: String,
        @Body body: ReqContent
    ): ResContentAdd

    //PUT Content Edit
    @PUT("chapter/{chapterId}")
    suspend fun putContentEdit(
        @Path("chapterId") chapterId: String,
        @Body body: ReqContent
    ) : ResContentEdit

    //DELETE Content Delete
    @DELETE("chapter/{chapterId}")
    suspend fun deleteContent(
        @Path("chapterId") chapterId: String,
    ) : ResContentDelete

    //GET Content Detail Part2
    @GET("secondPart/chapter/user/:{chapterId}")
    suspend fun getContentDetailPart2(
        @Path("chapterId") chapterId: String
    ): ResContentDetail

    //GET Content List Part2
    @GET("secondPart/chapter/list")
    suspend fun getContentListPart2(): ResContentList


    //POST Content Add Part2
    @POST("secondPart/chapter")
    suspend fun postContentAddPart2(
        @Body body: ReqContent
    ): ResContentAdd


    //PUT Content Edit Part2
    @PUT("secondPart/chapter/:{chapterId}")
    suspend fun putContentEditPart2(
        @Path("chapterId") chapterId: String,
        @Body body: ReqContent
    ) : ResContentEdit

    //DELETE Content Delete
    @DELETE("secondPart/chapter/:{chapterId}")
    suspend fun deleteContentPart2(
        @Path("chapterId") chapterId: String,
        @Body body: ReqContentDelete
    ) : ResContentDelete
}