package org.mascota.data.repository.home

import org.mascota.data.remote.datasource.home.HomeDataSource
import org.mascota.data.remote.model.response.home.ResHomePart1
import org.mascota.data.remote.model.response.home.ResHomePart2

class HomeRepositoryImpl(private val remoteDataSource: HomeDataSource) : HomeRepository {
    override suspend fun getHomePart1(path: String): ResHomePart1 =
        remoteDataSource.getHomePart1(path)

    override suspend fun getHomePart2(path: String): ResHomePart2 =
        remoteDataSource.getHomePart2(path)
}