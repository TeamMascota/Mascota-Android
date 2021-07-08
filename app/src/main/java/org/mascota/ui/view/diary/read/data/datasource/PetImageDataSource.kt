package org.mascota.ui.view.diary.read.data.datasource

interface PetImageDataSource {
    fun getPetImageUrlData(): List<String>
    fun getEmotionImageData() : List<Int>
}