package org.mascota.data.remote.datasource.profile

import org.mascota.data.remote.model.request.profile.ReqRegisterBook
import org.mascota.data.remote.model.request.profile.ReqRegisterPet
import org.mascota.data.remote.model.response.profile.ResRegisterBook
import org.mascota.data.remote.model.response.profile.ResRegisterPet

interface ProfileDataSource {
    //POST 반려동물 등록
    suspend fun postRegisterPet(body: ReqRegisterPet): ResRegisterPet

    //POST 책 등록
    suspend fun postRegisterBook(path : String, body: ReqRegisterBook): ResRegisterBook
}