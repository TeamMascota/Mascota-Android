package org.mascota.data.remote.api

import org.mascota.data.remote.model.request.diary.ReqDiaryDelete
import org.mascota.data.remote.model.request.diary.ReqPersonDiaryEdit
import org.mascota.data.remote.model.request.diary.ReqPersonDiaryWrite
import org.mascota.data.remote.model.request.diary.ReqPetDiaryWrite
import org.mascota.data.remote.model.response.diary.*
import retrofit2.http.*

interface DiaryService {
    //POST Diary Pet
    @POST("diary/pet")
    suspend fun postPetDiaryWrite(
        @Body body: ReqPetDiaryWrite
    ): ResPetDiaryWrite

    //GET Diary Read(Pet)
    @GET("diary/pet/:{petDiaryId}")
    suspend fun postPetDiaryRead(
        @Path("petDiaryId") petDiaryId: String
    ): ResDiaryRead

    //PUT Diary Edit(Pet)
    @PUT("diary/pet/:{petDiaryId}")
    suspend fun putPetDiaryEdit(
        @Path("petDiaryId") petDiaryId: String,
        @Body body: ReqPetDiaryWrite
    ): ResDiaryEdit

    //DELETE Diary Delete(Pet)
    @DELETE("petDiary/putPetDiary/:{petDiaryId}")
    suspend fun deletePetDiary(
        @Path("petDiaryId") petDiaryId: String,
        @Body body: ReqDiaryDelete
    ): ResDiaryDelete

    //POST Diary Person
    @POST("diary/user")
    suspend fun postPersonDiaryWrite(
        @Body body: ReqPersonDiaryWrite
    ): ResPersonDiaryWrite

    @GET("secondPart/diary/user/:{diaryId}")
    suspend fun getPersonDiaryRead(
        @Path("diaryId") diaryId: String
    ) : ResPersonDiaryRead

    //PUT Diary Edit(Person)
    @PUT("diary/user/:{petDiaryId}")
    suspend fun putPersonDiaryEdit(
        @Path("petDiaryId") petDiaryId: String,
        @Body body: ReqPersonDiaryEdit
    ): ResDiaryEdit

    //PUT Diary Delete(Person)
    @DELETE("userDiary/putUserDiary/:{diaryId}")
    suspend fun deletePersonDiary(
        @Path("diaryId") diaryId: String,
        @Body body: ReqDiaryDelete
    ): ResDiaryDelete
}