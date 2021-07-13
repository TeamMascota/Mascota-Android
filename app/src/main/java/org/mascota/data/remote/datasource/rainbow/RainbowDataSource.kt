package org.mascota.data.remote.datasource.rainbow

import org.mascota.data.remote.model.request.rainbow.ReqRainbowEpilogue
import org.mascota.data.remote.model.response.rainbow.*

interface RainbowDataSource {
    //GET Rainbow Home
    suspend fun getRainbowHome(userId: String, petId: String): ResRainbowHome

    //GET Rainbow Pet Select
    suspend fun getFarewellSelect(id: String): ResFarewellSelect

    //PUT Rainbow Content
    suspend fun putRainbowContent(path: String): ResRainbowContent

    //DELETE Rainbow Quit
    suspend fun deleteFarewellQuit(path: String): ResFarewellQuit

    //GET Rainbow Book
    suspend fun getRainbowBook(userId: String, petId: String): ResRainbowBook

    //GET Rainbow Best Moment
    suspend fun getRainbowBestMoment(userId: String, petId: String): ResBestMoment

    //GET Rainbow Pet Name
    suspend fun getRainbowPetName(path: String): ResPetName

    //POST Rainbow Epilogue
    suspend fun postRainbowEpilogue(body: ReqRainbowEpilogue): ResRainbowEpilogue

    //GET Sub Home
    suspend fun getRainbowSubHome(userId: String, petId: String): ResRainbowSubHome
}