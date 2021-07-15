package org.mascota.ui.view.diary.read

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.data.remote.model.response.diary.ResDiaryRead
import org.mascota.databinding.ActivityDiaryReadBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.content.detail.adapter.ContentDetailMonthAdapter
import org.mascota.ui.view.content.edit.ContentEditActivity
import org.mascota.ui.view.diary.BottomSheet
import org.mascota.ui.view.diary.DiaryWriteActivity
import org.mascota.ui.view.diary.read.adapter.EmotionImageAdapter
import org.mascota.ui.view.diary.read.adapter.PetImagePagerAdapter
import org.mascota.ui.view.diary.read.data.datasource.LocalPetImageDataSource
import org.mascota.ui.view.diary.read.data.model.DiaryEmoDataInfo
import org.mascota.ui.viewmodel.ContentViewModel
import org.mascota.ui.viewmodel.DiaryViewModel
import org.mascota.util.StringUtil.makeEpisodeText
import org.mascota.util.StringUtil.makeTogetherDay
import org.mascota.util.StringUtil.makeTotalText
import org.mascota.util.extension.setTextPartialColor


class DiaryReadActivity : BindingActivity<ActivityDiaryReadBinding>(R.layout.activity_diary_read) {
    private val petImageDataSource = LocalPetImageDataSource()
    private val diaryViewModel : DiaryViewModel by viewModel()
    private lateinit var petImagePagerAdapter: PetImagePagerAdapter
    private lateinit var emotionImageAdapter: EmotionImageAdapter

    private var _diaryIdList = arrayListOf<String>()

    fun setDiaryIdList(diaryIdList : ArrayList<String>){
        _diaryIdList = diaryIdList
    }

    override fun initView() {
        getResPetDiaryRead()
        observeResPetDiaryRead()
        initPetImagePagerAdapter()
        initEmotionImageAdapter()
        setBackBtnClickListener()
        checkIntentFrom()
        initClickEditBtn()
    }


    private fun initClickEditBtn() {
        binding.tvEdit.setOnClickListener {
            showEditBottomSheet()
        }

    }

    private fun showEditBottomSheet() {
        val bottomSheet = EditBottomSheet()
        bottomSheet.setCallbackButtonClickListener {
            // 내용 수정 버튼 눌렀을 떄
            startActivity(Intent(this, DiaryWriteActivity::class.java))
        }

        bottomSheet.setCallbackDeleteBtnClickListener {
            //내용 삭제 버튼 눌렀을 떄
            Log.d("다이얼로그 창","나중에 추가해랑")
        }

        bottomSheet.show(supportFragmentManager.beginTransaction(), bottomSheet.tag)
    }


    private fun observeResPetDiaryRead() {
        diaryViewModel.diaryReadPet.observe(this){
            it.data.petDiary.apply{
                with(binding){
                    if(episode != 0){
                        tvEpisode.text = makeEpisodeText(episode)
                    }else {
                        tvEpisode.visibility = View.INVISIBLE
                    }
                    tvTitle.text = title
                    tvDate.text = date
                    tvContent.text = contents
                    tvTogetherDay.text = tvTogetherDay.setTextPartialColor(
                        R.color.maco_orange,
                        6,
                        6 + timeTogether.toString().length,
                        makeTogetherDay(timeTogether)
                    )
                    when {
                        bookImg.isEmpty() -> {
                            isPictureExist = false
                            isPictures = false
                        }
                        bookImg.size == 1 -> {
                            isPictureExist = true
                            isPictures = false
                        }
                        else -> {
                            isPictureExist = true
                            isPictures = true
                        }
                    }
                }
                petImagePagerAdapter.petImgUrlList = bookImg

                val diaryEmoDataList = mutableListOf<DiaryEmoDataInfo>()

                var filteredList = listOf<ResDiaryRead.Data.PetDiary.Feeling>()
                for(kindIdx in 0..2){
                    for(feelingIdx in 0..5){
                        filteredList = feelingList.filter{it.kind == kindIdx && it.feeling == feelingIdx}
                        Log.d("filteredList", "$filteredList")
                        if(filteredList.isNotEmpty()) {
                            val diaryImgsList = mutableListOf<String>()
                            for(element in filteredList){
                                diaryImgsList.add(element.petImg)
                            }
                            diaryEmoDataList.add(
                                DiaryEmoDataInfo(feelingIdx, kindIdx, diaryImgsList)
                            )
                        }
                    }
                }
                emotionImageAdapter.emotionList = diaryEmoDataList
            }
        }
    }

    private fun getResPetDiaryRead() {
        diaryViewModel.getPetDiaryRead()
    }

    private fun postPetDiaryId(id: String) {
        diaryViewModel.postPetDiaryId(id)
    }

    private fun initPetImagePagerAdapter() {
        petImagePagerAdapter = PetImagePagerAdapter().apply {
            binding.vpPetImg.adapter = this
            //petImgUrlList = petImageDataSource.getPetImageUrlData()
            binding.springDotsIndicator.setViewPager2(binding.vpPetImg)
        }
    }

    private fun initEmotionImageAdapter() {
        emotionImageAdapter = EmotionImageAdapter().apply {
            binding.rvEmotion.adapter = this
            //emotionNumList = petImageDataSource.getEmotionImageData()
        }
    }

    private fun setBackBtnClickListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun checkIntentFrom() {
        intent.getStringArrayListExtra("petDiaryId")?.let { setDiaryIdList(it) }
        when (_diaryIdList.size) {
            1 -> {
                postPetDiaryId(_diaryIdList[0])
                with(binding) {
                    btnLeft.isEnabled = false
                    btnRight.isEnabled = false
                }
            }
            else -> {
                for(i in 0 until _diaryIdList.size){
                    setArrowBtnClickListener(_diaryIdList[i])
                    when (i) {
                        0 -> {
                            with(binding) {
                                btnLeft.isEnabled = false
                                btnRight.isEnabled = true
                            }
                        }
                        _diaryIdList.size - 1 -> {
                            with(binding) {
                                btnLeft.isEnabled = true
                                btnRight.isEnabled = false
                            }
                        }
                        else -> {
                            with(binding) {
                                btnLeft.isEnabled = true
                                btnRight.isEnabled = true
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setArrowBtnClickListener(id: String) {
        binding.btnLeft.setOnClickListener {
            postPetDiaryId(id)
        }
        binding.btnRight.setOnClickListener {
            postPetDiaryId(id)
        }
    }




}