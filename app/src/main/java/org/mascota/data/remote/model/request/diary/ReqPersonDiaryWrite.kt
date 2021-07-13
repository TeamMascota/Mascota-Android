package org.mascota.data.remote.model.request.diary

data class ReqPersonDiaryWrite(
    // 작가 일기 작성
    val feeling: Int,
    val diaryImages: List<String>,
    val title: String,
    val contents: String,
    val chapterId: String
)