package org.mascota.data.remote.datasource.content

import org.mascota.data.remote.api.ContentService
import org.mascota.data.remote.model.request.content.ReqContent
import org.mascota.data.remote.model.request.content.ReqContentDelete
import org.mascota.data.remote.model.response.content.*

class RemoteContentDataSource(private val service: ContentService) : ContentDataSource {
    override suspend fun getContentDetail(path: String): ResContentDetail =
        service.getContentDetail(path)

    override suspend fun getContentList(path: String): ResContentList = service.getContentList(path)

    override suspend fun postContentAdd(path: String, body: ReqContent): ResContentAdd =
        service.postContentAdd(path, body)

    override suspend fun putContentEdit(path: String, body: ReqContent): ResContentEdit =
        service.putContentEdit(path, body)

    override suspend fun deleteContent(path: String, body: ReqContentDelete): ResContentDelete =
        service.deleteContent(path, body)

    override suspend fun getContentDetailPart2(path: String): ResContentDetail =
        service.getContentDetailPart2(path)

    override suspend fun getContentListPart2(): ResContentList = service.getContentListPart2()

    override suspend fun postContentAddPart2(body: ReqContent): ResContentAdd =
        service.postContentAddPart2(body)

    override suspend fun putContentEditPart2(path: String, body: ReqContent): ResContentEdit =
        service.putContentEditPart2(path, body)

    override suspend fun deleteContentPart2(
        path: String,
        body: ReqContentDelete
    ): ResContentDelete = service.deleteContentPart2(path, body)

}