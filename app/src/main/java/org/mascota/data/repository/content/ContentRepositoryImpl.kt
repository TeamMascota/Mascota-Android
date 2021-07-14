package org.mascota.data.repository.content

import org.mascota.data.remote.datasource.content.ContentDataSource
import org.mascota.data.remote.model.request.content.ReqContent
import org.mascota.data.remote.model.request.content.ReqContentDelete
import org.mascota.data.remote.model.response.content.*

class ContentRepositoryImpl(private val remoteDataSource: ContentDataSource) : ContentRepository{
    override suspend fun getContentDetail(path: String): ResContentDetail = remoteDataSource.getContentDetail(path)

    override suspend fun getContentList(path: String): ResContentList = remoteDataSource.getContentList(path)

    override suspend fun postContentAdd(path: String, body: ReqContent): ResContentAdd = remoteDataSource.postContentAdd(path, body)

    override suspend fun putContentEdit(path: String, body: ReqContent): ResContentEdit = remoteDataSource.putContentEdit(path, body)

    override suspend fun deleteContent(path: String): ResContentDelete = remoteDataSource.deleteContent(path)

    override suspend fun getContentDetailPart2(path: String): ResContentDetail = remoteDataSource.getContentDetailPart2(path)

    override suspend fun getContentListPart2(): ResContentList = remoteDataSource.getContentListPart2()

    override suspend fun postContentAddPart2(body: ReqContent): ResContentAdd = remoteDataSource.postContentAddPart2(body)

    override suspend fun putContentEditPart2(path: String, body: ReqContent): ResContentEdit = remoteDataSource.putContentEditPart2(path, body)

    override suspend fun deleteContentPart2(path: String, body: ReqContentDelete): ResContentDelete = remoteDataSource.deleteContentPart2(path, body)
}