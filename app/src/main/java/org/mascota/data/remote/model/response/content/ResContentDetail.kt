package org.mascota.data.remote.model.response.content

data class ResContentDetail(
    // 목차별 일기 조회
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val petChapterDiary: PetChapterDiary
    ) {
        data class PetChapterDiary(
            val chapterId: String,
            val chapter: Int,
            val chapterTitle: String,
            val monthly: List<Monthly>
        ) {
            data class Monthly(
                val episodePerMonthCount: Int,
                val month: Int,
                val diaries: List<Diary>
            ) {
                data class Diary(
                    val diaryId: String,
                    val title: String,
                    val contents: String,
                    val episode: Int,
                    val image: String,
                    val feelingCount: Int,
                    val feeling: Int,
                    val date: String,
                    val weekday: String,
                    val kind: Int
                )
            }
        }
    }
}