package org.mascota.ui.view.calendar.data.datasource

import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_ANGRY
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_BORING
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_JOY
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_LOVE
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_SAD
import org.mascota.ui.view.calendar.CalendarFragment.Companion.DOG_USUAL
import org.mascota.ui.view.calendar.CalendarFragment.Companion.EMPTY
import org.mascota.ui.view.calendar.data.model.CalendarData

class LocalTempCalendarDataSource : TempCalendarDataSource {
    override fun getCalendarData(): List<CalendarData> = listOf(
        CalendarData(DOG_ANGRY, "3", false),
        CalendarData(DOG_SAD, "0", true),
        CalendarData(DOG_USUAL, "0", true),
        CalendarData(EMPTY, "0", true),
        CalendarData(DOG_LOVE, "4", false),
        CalendarData(EMPTY, "0", true),
        CalendarData(DOG_BORING, "0", true),
        CalendarData(DOG_JOY, "3", false),
        CalendarData(EMPTY, "0", true),
        CalendarData(DOG_ANGRY, "3", false),
        CalendarData(EMPTY, "0", true),
        CalendarData(DOG_ANGRY, "3", false),
        CalendarData(DOG_ANGRY, "3", false),
        CalendarData(EMPTY, "0", true),
        CalendarData(DOG_LOVE, "0", true),
        CalendarData(DOG_ANGRY, "3", false)
    )
}