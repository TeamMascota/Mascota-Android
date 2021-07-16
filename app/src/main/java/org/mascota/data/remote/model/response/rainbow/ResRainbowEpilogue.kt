package org.mascota.data.remote.model.response.rainbow

data class ResRainbowEpilogue(
    // 무지개 에필로그
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val rainbowMainPage: RainbowMainPage
    ) {
        data class RainbowMainPage(
            val title: String,
            val bookImg: String,
            val rainbowCheck : Boolean,
            val memories: List<Memory>,
            val help : List<Help>
        ) {
            data class Memory(
                val diaryId : String,
                val title: String,
                val contents: String,
                val date: String,
                val feeling: Int
            )
            data class Help(
                val classification: String,
                val title: String,
                val url: String
            )
        }
    }
}