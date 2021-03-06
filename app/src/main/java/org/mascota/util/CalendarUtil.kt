package org.mascota.util

import java.text.SimpleDateFormat
import java.util.*

object CalendarUtil {
    private val simpleDateFormat = SimpleDateFormat("yyyy년 M월", Locale.KOREA)
    private val simpleToBeFamilyDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    private val simpleStoryDateFormat = SimpleDateFormat("yyyy년 M월의 이야기", Locale.KOREA)
    private val remoteDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA)

    fun convertCalendarToString(calendar: Calendar): String = simpleDateFormat.format(calendar.time)

    fun convertCalendarToBeFamilyDateString(calendar: Calendar): String =
        simpleToBeFamilyDateFormat.format(calendar.time)

    fun convertCalendarToStoryDateString(calendar: Calendar): String =
        simpleStoryDateFormat.format(calendar.time)

    fun Calendar.isDaySame(calendar: Calendar): Boolean {
        return get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
                && get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
                && get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun convertStringToCalendar(date: String): Calendar {
        val calendar = Calendar.getInstance()

        calendar.time = requireNotNull(remoteDateFormat.parse(date))
        return calendar
    }

    fun initCalendar(calendar: Calendar): Calendar {
        return calendar.apply {
            set(Calendar.DAY_OF_MONTH, 1)
        }
    }
}