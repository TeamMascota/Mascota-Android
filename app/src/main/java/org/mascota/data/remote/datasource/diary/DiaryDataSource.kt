package org.mascota.data.remote.datasource.diary

import org.mascota.data.remote.model.request.diary.ReqDiaryDelete
import org.mascota.data.remote.model.request.diary.ReqPersonDiaryEdit
import org.mascota.data.remote.model.request.diary.ReqPersonDiaryWrite
import org.mascota.data.remote.model.request.diary.ReqPetDiaryWrite
import org.mascota.data.remote.model.response.diary.*

interface DiaryDataSource {
    //POST Diary Pet
    suspend fun postPetDiaryWrite(body: ReqPetDiaryWrite): ResPetDiaryWrite

    //GET Diary Read(Pet)
    suspend fun getPetDiaryRead(path: String): ResDiaryRead

    //PUT Diary Edit(Pet)
    suspend fun putPetDiaryEdit(path: String, body: ReqPetDiaryWrite): ResDiaryEdit

    //DELETE Diary Delete(Pet)
    suspend fun deletePetDiary(path: String, body: ReqDiaryDelete): ResDiaryDelete

    //POST Diary Person
    suspend fun postPersonDiaryWrite(body: ReqPersonDiaryWrite): ResPersonDiaryWrite

    suspend fun getPersonDiaryRead(path: String): ResPersonDiaryRead

    //PUT Diary Edit(Person)
    suspend fun putPersonDiaryEdit(path: String, body: ReqPersonDiaryEdit): ResDiaryEdit

    //PUT Diary Delete(Person)
    suspend fun deletePersonDiary(path: String, body: ReqDiaryDelete): ResDiaryDelete

    //Get Animal List
    suspend fun getAnimalInfo(): ResPetInfo
}