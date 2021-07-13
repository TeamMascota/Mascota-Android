package org.mascota.data.remote.api

import org.mascota.data.remote.model.response.calendar.ResCalendar
import retrofit2.http.GET
import retrofit2.http.Path

interface CalendarService {
    //GET Calendar
    @GET("calendar/:{year}/:{month}/:{part}")
    suspend fun getCalendar(
        @Path("year") userId: Int,
        @Path("month") month: Int,
        @Path("part") part: Int
    ): ResCalendar
}