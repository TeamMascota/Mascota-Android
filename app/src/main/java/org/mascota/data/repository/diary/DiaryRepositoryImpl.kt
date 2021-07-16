package org.mascota.data.repository.diary

import org.mascota.data.remote.datasource.diary.DiaryDataSource
import org.mascota.data.remote.model.request.diary.ReqDiaryDelete
import org.mascota.data.remote.model.request.diary.ReqPersonDiaryEdit
import org.mascota.data.remote.model.request.diary.ReqPersonDiaryWrite
import org.mascota.data.remote.model.request.diary.ReqPetDiaryWrite
import org.mascota.data.remote.model.response.diary.*

class DiaryRepositoryImpl(private val remoteDataSource : DiaryDataSource) : DiaryRepository{
    override suspend fun postPetDiaryWrite(body: ReqPetDiaryWrite): ResPetDiaryWrite = remoteDataSource.postPetDiaryWrite(body)

    override suspend fun getPetDiaryRead(path: String): ResDiaryRead = remoteDataSource.getPetDiaryRead(path)

    override suspend fun putPetDiaryEdit(path: String, body: ReqPetDiaryWrite): ResDiaryEdit = remoteDataSource.putPetDiaryEdit(path, body)

    override suspend fun deletePetDiary(path: String, body: ReqDiaryDelete): ResDiaryDelete = remoteDataSource.deletePetDiary(path, body)

    override suspend fun postPersonDiaryWrite(body: ReqPersonDiaryWrite): ResPersonDiaryWrite = remoteDataSource.postPersonDiaryWrite(body)

    override suspend fun getPersonDiaryRead(path: String): ResPersonDiaryRead = remoteDataSource.getPersonDiaryRead(path)

    override suspend fun putPersonDiaryEdit(path: String, body: ReqPersonDiaryEdit): ResDiaryEdit = remoteDataSource.putPersonDiaryEdit(path, body)

    override suspend fun deletePersonDiary(path: String, body: ReqDiaryDelete): ResDiaryDelete = remoteDataSource.deletePersonDiary(path, body)

    override suspend fun getAnimalInfo(): ResPetInfo = remoteDataSource.getAnimalInfo()
}