package org.mascota.ui.view.diary.read.data.datasource

import org.mascota.ui.view.diary.read.data.model.DiaryPetFeelingInfoData

interface PetImageDataSource {
    fun getPetImageUrlData(): List<String>
    fun getEmotionImageData() : List<DiaryPetFeelingInfoData>
}