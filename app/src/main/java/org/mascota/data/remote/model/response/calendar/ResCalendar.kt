package org.mascota.data.remote.model.response.calendar

data class ResCalendar(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val name: String,
        val part: String,
        val nextEpilogue: Int,
        val calendar: Calendar
    ) {
        data class Calendar(
            val year: String,
            val month: String,
            val date: List<Date>
        ) {
            data class Date(
                val days: Int,
                val kind: Int,
                val id: List<String>,
                val feeling: Int
            )
        }
    }
}