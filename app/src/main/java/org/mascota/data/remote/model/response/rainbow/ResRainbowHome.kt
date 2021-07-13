package org.mascota.data.remote.model.response.rainbow

data class ResRainbowHome(
    // 무지개 홈 화면
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
            val rainbowCheck: Boolean,
            val memories: List<Memory>,
            val help: List<Help>
        ) {
            data class Memory(
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