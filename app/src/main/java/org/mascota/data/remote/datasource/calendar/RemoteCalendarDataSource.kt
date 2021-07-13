package org.mascota.data.remote.datasource.calendar

import org.mascota.data.remote.api.CalendarService
import org.mascota.data.remote.model.response.calendar.ResCalendar

class RemoteCalendarDataSource(private val service : CalendarService) : CalendarDataSource {
    override suspend fun getCalendar(year: Int, month: Int, day: Int): ResCalendar = service.getCalendar(year, month, day)
}