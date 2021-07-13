package org.mascota.data.remote.datasource.home

import org.mascota.data.remote.api.HomeService
import org.mascota.data.remote.model.response.home.ResHomePart1
import org.mascota.data.remote.model.response.home.ResHomePart2

class RemoteHomeDataSource(private val service: HomeService) : HomeDataSource {
    override suspend fun getHomePart1(path: String): ResHomePart1 = service.getHomePart1(path)

    override suspend fun getHomePart2(path: String): ResHomePart2 = service.getHomePart2(path)
}