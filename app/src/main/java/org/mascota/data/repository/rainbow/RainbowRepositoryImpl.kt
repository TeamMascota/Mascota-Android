package org.mascota.data.repository.rainbow

import org.mascota.data.remote.datasource.rainbow.RainbowDataSource
import org.mascota.data.remote.model.request.rainbow.ReqRainbowEpilogue
import org.mascota.data.remote.model.response.rainbow.*

class RainbowRepositoryImpl(private val remoteDataSource: RainbowDataSource) : RainbowRepository {
    override suspend fun getRainbowHome(userId: String, petId: String): ResRainbowHome =
        remoteDataSource.getRainbowHome(userId, petId)

    override suspend fun getFarewellSelect(): ResFarewellSelect =
        remoteDataSource.getFarewellSelect()

    override suspend fun putRainbowContent(path: String): ResRainbowContent =
        remoteDataSource.putRainbowContent(path)

    override suspend fun deleteFarewellQuit(path: String): ResFarewellQuit =
        remoteDataSource.deleteFarewellQuit(path)

    override suspend fun getRainbowBook(userId: String, petId: String): ResRainbowBook =
        remoteDataSource.getRainbowBook(userId, petId)

    override suspend fun getRainbowBestMoment(userId: String, petId: String): ResBestMoment =
        remoteDataSource.getRainbowBestMoment(userId, petId)

    override suspend fun getRainbowPetName(petId: String): ResPetName =
        remoteDataSource.getRainbowPetName(petId)

    override suspend fun postRainbowEpilogue(body: ReqRainbowEpilogue): ResRainbowEpilogue =
        remoteDataSource.postRainbowEpilogue(body)

    override suspend fun getRainbowSubHome(userId: String, petId: String): ResRainbowSubHome =
        remoteDataSource.getRainbowSubHome(userId, petId)
}