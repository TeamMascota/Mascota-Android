package org.mascota.data.repository.calendar

import org.mascota.data.remote.datasource.calendar.CalendarDataSource
import org.mascota.data.remote.model.response.calendar.ResCalendar

class CalendarRepositoryImpl(private val remoteDataSource: CalendarDataSource) :
    CalendarRepository {
    override suspend fun getCalendar(year: Int, month: Int, day: Int): ResCalendar =
        remoteDataSource.getCalendar(year, month, day)
}