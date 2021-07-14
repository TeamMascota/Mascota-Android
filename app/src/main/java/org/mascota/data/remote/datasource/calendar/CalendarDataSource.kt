package org.mascota.data.remote.datasource.calendar

import org.mascota.data.remote.model.response.calendar.ResCalendar

interface CalendarDataSource {
    suspend fun getCalendar(year : Int, month : Int, part : Int) : ResCalendar
}