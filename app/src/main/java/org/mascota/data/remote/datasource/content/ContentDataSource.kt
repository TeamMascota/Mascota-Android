package org.mascota.data.remote.datasource.content

import org.mascota.data.remote.model.request.content.ReqContent
import org.mascota.data.remote.model.response.content.*

interface ContentDataSource {
    //GET Content Detail
    suspend fun getContentDetail(path: String): ResContentDetail

    //GET Content List
    suspend fun getContentList(path: String): ResContentList

    //POST Content Add
    suspend fun postContentAdd(path: String, body: ReqContent): ResContentAdd

    //PUT Content Edit
    suspend fun putContentEdit(path: String, body: ReqContent): ResContentEdit

    //DELETE Content Delete
    suspend fun deleteContent(path: String): ResContentDelete

    //GET Content Detail Part2
    suspend fun getContentDetailPart2(path: String): ResPart2ContentDetail

    //GET Content List Part2
    suspend fun getContentListPart2(): ResPart2ContentList


    //POST Content Add Part2
    suspend fun postContentAddPart2(body: ReqContent): ResContentAdd


    //PUT Content Edit Part2
    suspend fun putContentEditPart2(path: String, body: ReqContent): ResContentEdit

    //DELETE Content Delete
    suspend fun deleteContentPart2(path: String): ResContentDelete
}