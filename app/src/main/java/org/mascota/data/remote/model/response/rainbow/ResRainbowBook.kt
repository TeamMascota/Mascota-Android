package org.mascota.data.remote.model.response.rainbow

data class ResRainbowBook(
    //이별의 순간 책
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
)  {
    data class Data(
        val diaryCount: Int,
        val dayTogether: Int,
        val bookInfo: BookInfo
    ) {
        data class BookInfo(
            val title: String,
            val bookImg: String,
            val author : String
        )
    }
}