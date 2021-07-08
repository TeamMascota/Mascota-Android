package org.mascota.ui.view.rainbow.data.model

import java.util.*

data class RainbowInfoData(
    val diaryList : List<Data>
){
    data class Data(
        val Date: Calendar,
        val title: String,
        val content: String,
        val emo : Int
    )
}