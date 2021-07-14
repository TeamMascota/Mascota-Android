package org.mascota.data.remote.model.response.content

data class ResPart2ContentAdd(
    // 목차 추가
    val status: Int,
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