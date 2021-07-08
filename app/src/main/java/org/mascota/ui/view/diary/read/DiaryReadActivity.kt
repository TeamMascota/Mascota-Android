package org.mascota.ui.view.diary.read

import org.mascota.R
import org.mascota.databinding.ActivityDiaryReadBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.diary.read.adapter.PetImagePagerAdapter
import org.mascota.ui.view.diary.read.data.datasource.LocalPetImageDataSource

class DiaryReadActivity : BindingActivity<ActivityDiaryReadBinding>(R.layout.activity_diary_read) {

    override fun initView() {
        initPetImagePagerAdapter()
    }

    private fun initPetImagePagerAdapter() {
        PetImagePagerAdapter().apply {
            binding.vpPetImg.adapter = this
            val petImageDataSource = LocalPetImageDataSource()
            petImgUrlList = petImageDataSource.getPetImageUrlData()
        }
    }
}