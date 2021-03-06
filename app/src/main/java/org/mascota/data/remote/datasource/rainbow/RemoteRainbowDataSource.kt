package org.mascota.data.remote.datasource.rainbow

import org.mascota.data.remote.api.RainbowService
import org.mascota.data.remote.model.request.rainbow.ReqRainbowEpilogue
import org.mascota.data.remote.model.response.rainbow.*

class RemoteRainbowDataSource(private val service : RainbowService) : RainbowDataSource {
    override suspend fun getRainbowHome(userId: String, petId: String): ResRainbowHome = service.getRainbowHome(userId, petId)

    override suspend fun getFarewellSelect(): ResFarewellSelect = service.getFarewellSelect()

    override suspend fun putRainbowContent(path: String): ResRainbowContent = service.putRainbowContent(path)

    override suspend fun deleteFarewellQuit(path: String): ResFarewellQuit = service.deleteFarewellQuit(path)
    override suspend fun getRainbowBook(petId: String): ResRainbowBook = service.getRainbowBook(petId)


    override suspend fun getRainbowBestMoment(userId: String, petId: String): ResBestMoment = service.getRainbowBestMoment(userId, petId)

    override suspend fun getRainbowPetName(path: String): ResPetName = service.getRainbowPetName(path)

    override suspend fun postRainbowEpilogue(userId: String,petId: String, body: ReqRainbowEpilogue): ResRainbowEpilogue = service.postRainbowEpilogue(userId,petId,body)

    override suspend fun getRainbowSubHome(userId: String, petId: String): ResRainbowSubHome = service.getRainbowSubHome(userId, petId)

    }

