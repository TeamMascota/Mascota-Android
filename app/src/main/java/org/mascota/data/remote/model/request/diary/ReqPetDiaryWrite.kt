package org.mascota.data.remote.model.request.diary

data class ReqPetDiaryWrite(
    // 반려동물 일기 작성
    val character: List<Character>,
    val title: String,
    val diaryImages: List<String>,
    val contents: String,
    val date: String,
    val _id: String
) {
    data class Character(
        val _id: String,
        val feeling: Int
    )
}