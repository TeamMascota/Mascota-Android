package org.mascota.data.remote.model.response.rainbow

data class ResRainbowContent(
    // 무지개 다리 멘트
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val partingRainbowBridge: PartingRainbowBridge
    ) {
        data class PartingRainbowBridge(
            val contents: String,
            val diaryCount : Int
        )
    }
}