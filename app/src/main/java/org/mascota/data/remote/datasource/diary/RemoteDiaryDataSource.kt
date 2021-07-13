package org.mascota.data.remote.datasource.diary

import org.mascota.data.remote.api.DiaryService
import org.mascota.data.remote.model.request.diary.ReqDiaryDelete
import org.mascota.data.remote.model.request.diary.ReqPersonDiaryEdit
import org.mascota.data.remote.model.request.diary.ReqPersonDiaryWrite
import org.mascota.data.remote.model.request.diary.ReqPetDiaryWrite
import org.mascota.data.remote.model.response.diary.*

class RemoteDiaryDataSource(private val service: DiaryService) : DiaryDataSource {
    override suspend fun postPetDiaryWrite(body: ReqPetDiaryWrite): ResPetDiaryWrite =
        service.postPetDiaryWrite(body)

    override suspend fun postPetDiaryRead(path: String): ResDiaryRead =
        service.postPetDiaryRead(path)

    override suspend fun putPetDiaryEdit(path: String, body: ReqPetDiaryWrite): ResDiaryEdit =
        service.putPetDiaryEdit(path, body)

    override suspend fun deletePetDiary(path: String, body: ReqDiaryDelete): ResDiaryDelete =
        service.deletePetDiary(path, body)

    override suspend fun postPersonDiaryWrite(body: ReqPersonDiaryWrite): ResPersonDiaryWrite =
        service.postPersonDiaryWrite(body)

    override suspend fun getPersonDiaryRead(path: String): ResPersonDiaryRead =
        service.getPersonDiaryRead(path)

    override suspend fun putPersonDiaryEdit(path: String, body: ReqPersonDiaryEdit): ResDiaryEdit =
        service.putPersonDiaryEdit(path, body)

    override suspend fun deletePersonDiary(path: String, body: ReqDiaryDelete): ResDiaryDelete =
        service.deletePersonDiary(path, body)
}