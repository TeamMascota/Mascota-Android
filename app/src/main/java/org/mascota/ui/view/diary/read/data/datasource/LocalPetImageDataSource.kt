package org.mascota.ui.view.diary.read.data.datasource

import org.mascota.ui.view.diary.read.data.model.DiaryPetFeelingInfoData

class LocalPetImageDataSource: PetImageDataSource {
    override fun getPetImageUrlData(): List<String> = listOf<String>(
        "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg",
        "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg",
        "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg",
        "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg",
        "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg"
    )

    override fun getEmotionImageData(): List<DiaryPetFeelingInfoData> = listOf(
        DiaryPetFeelingInfoData(2, listOf<String>("https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg",
            "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg")),
        DiaryPetFeelingInfoData(3, listOf<String>("https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg")),
        DiaryPetFeelingInfoData(5, listOf<String>("https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg",
            "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg"))
    )

}