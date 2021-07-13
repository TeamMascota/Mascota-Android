package org.mascota.data.repository.home

import org.mascota.data.remote.model.response.home.ResHomePart1
import org.mascota.data.remote.model.response.home.ResHomePart2

interface HomeRepository {
    //GET Home Part1
    suspend fun getHomePart1(path: String): ResHomePart1

    //GET Home Part2
    suspend fun getHomePart2(path: String): ResHomePart2
}