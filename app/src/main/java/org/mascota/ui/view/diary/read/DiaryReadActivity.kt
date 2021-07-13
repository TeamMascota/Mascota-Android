package org.mascota.ui.view.diary.read

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
        initEmotionImageAdapter()
        setBackBtnClickListener()
    }

    private fun initPetImagePagerAdapter() {
        PetImagePagerAdapter().apply {
            binding.vpPetImg.adapter = this
            petImgUrlList = petImageDataSource.getPetImageUrlData()
            binding.springDotsIndicator.setViewPager2(binding.vpPetImg)
        }
    }

    private fun initEmotionImageAdapter() {
        EmotionImageAdapter().apply {
            binding.rvEmotion.adapter = this
            emotionNumList = petImageDataSource.getEmotionImageData()
        }
    }

    private fun setBackBtnClickListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}