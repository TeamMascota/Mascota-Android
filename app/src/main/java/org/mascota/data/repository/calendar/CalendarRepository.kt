package org.mascota.data.repository.calendar

import org.mascota.data.remote.model.response.calendar.ResCalendar

interface CalendarRepository {
    suspend fun getCalendar(year : Int, month : Int, day : Int) : ResCalendar
}