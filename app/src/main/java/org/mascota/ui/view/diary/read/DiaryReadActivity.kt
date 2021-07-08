package org.mascota.ui.view.diary.read

import com.google.android.material.tabs.TabLayoutMediator
import org.mascota.R
import org.mascota.databinding.ActivityDiaryReadBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.diary.read.adapter.EmotionImageAdapter
import org.mascota.ui.view.diary.read.adapter.PetImagePagerAdapter
import org.mascota.ui.view.diary.read.data.datasource.LocalPetImageDataSource


class DiaryReadActivity : BindingActivity<ActivityDiaryReadBinding>(R.layout.activity_diary_read) {
    private val petImageDataSource = LocalPetImageDataSource()

    override fun initView() {
        initPetImagePagerAdapter()
        connectPagerToIndicator()
        initEmotionImageAdapter()
    }

    private fun initPetImagePagerAdapter() {
        PetImagePagerAdapter().apply {
            binding.vpPetImg.adapter = this
            petImgUrlList = petImageDataSource.getPetImageUrlData()
        }
    }

    private fun connectPagerToIndicator() {
        TabLayoutMediator(binding.tabIndicator, binding.vpPetImg) { tab, position -> }.attach()
    }

    private fun initEmotionImageAdapter() {
        EmotionImageAdapter().apply {
            binding.rvEmotion.adapter = this
            emotionNumList = petImageDataSource.getEmotionImageData()
        }
    }
}