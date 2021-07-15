package org.mascota.data.remote.model.response.content

data class ResPart2ContentList(
    // 목차 리스트
    val status : Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data (
        val tableContents: List<TableContent>
    ){
        data class TableContent(
            val _id: String,
            val chapter: Int,
            val title: String
        )
    }
}