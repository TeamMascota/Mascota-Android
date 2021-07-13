package org.mascota.data.remote.model.response.calendar

data class ResCalendar(
    // 캘린더
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val name: String,
        val part: Int,
        val calendar: Calendar
    ) {
        data class Calendar(
            val year: Int,
            val month: String,
            val date: List<Date>
        ) {
            data class Date(
                val days: Int,
                val kind: Int,
                val diaryId: List<String>,
                val feeling: Int
            )
        }
    }
}