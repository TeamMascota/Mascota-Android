package org.mascota.util

import java.text.SimpleDateFormat
import java.util.*

object CalendarUtil {
    private val simpleDateFormat = SimpleDateFormat("yyyy년 M월", Locale.KOREA)

    fun convertCalendarToString(calendar: Calendar): String = simpleDateFormat.format(calendar.time)

    fun Calendar.isDaySame(calendar: Calendar): Boolean {
        return get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
                && get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
                && get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun Calendar.isMonthSame(calendar: Calendar): Boolean {
        return get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
                && get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
    }
}