package org.mascota.ui.view.diary.read.data.datasource

class LocalPetImageDataSource: PetImageDataSource {
    override fun getPetImageUrlData(): List<String> = listOf<String>(
        "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg",
        "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg",
        "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg"
    )
}