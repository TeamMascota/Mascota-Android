package org.mascota.ui.view.calendar.data.datasource

import org.mascota.ui.view.calendar.data.model.CalendarData

interface CalendarDataSource {
    fun getCalendarData() : List<CalendarData>
}