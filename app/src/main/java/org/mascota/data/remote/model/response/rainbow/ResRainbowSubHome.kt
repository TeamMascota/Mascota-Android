package org.mascota.data.remote.model.response.rainbow

data class ResRainbowSubHome(
    // 2부에서 최고의순간
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val rainbowComment: String,
        val rainbowBestMoment: RainbowBestMoment
    ) {
        data class RainbowBestMoment(
            val pet: Pet,
            val theBestMoments: List<TheBestMoment>
        ) {
            data class Pet(
                val name: String,
                val kind: Int
            )

            data class TheBestMoment(
                val comment: String,
                val feeling: Int,
                val diaries: List<Diary>
            ) {
                data class Diary(
                    val chapter: Int,
                    val episode: Int,
                    val title: String,
                    val contents: String,
                    val date: String
                )
            }
        }
    }
}