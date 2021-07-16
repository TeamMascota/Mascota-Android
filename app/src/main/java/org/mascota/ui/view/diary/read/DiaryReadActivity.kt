package org.mascota.ui.view.diary.read

import android.app.Dialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.data.local.MascotaSharedPreference
import org.mascota.data.remote.model.response.diary.ResDiaryRead
import org.mascota.data.remote.model.response.diary.ResPersonDiaryRead
import org.mascota.databinding.ActivityDiaryReadBinding
import org.mascota.databinding.LayoutMascotaDialogBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.diary.DiaryWriteActivity
import org.mascota.ui.view.diary.read.adapter.EmotionImageAdapter
import org.mascota.ui.view.diary.read.adapter.PetImagePagerAdapter
import org.mascota.ui.view.diary.read.data.model.DiaryEmoDataInfo
import org.mascota.ui.viewmodel.DiaryViewModel
import org.mascota.util.DialogUtil
import org.mascota.util.StringUtil.makeEpisodeText
import org.mascota.util.StringUtil.makeTogetherDay
import org.mascota.util.extension.setTextPartialColor


class DiaryReadActivity : BindingActivity<ActivityDiaryReadBinding>(R.layout.activity_diary_read) {
    private val diaryViewModel: DiaryViewModel by viewModel()
    private lateinit var petImagePagerAdapter: PetImagePagerAdapter
    private lateinit var emotionImageAdapter: EmotionImageAdapter
    private lateinit var deleteDialogBinding: LayoutMascotaDialogBinding
    private lateinit var deleteDialog: Dialog

    private var _diaryIdList = arrayListOf<String>()

    private var from : Int = IS_CONTENT

    private fun setDiaryIdList(diaryIdList: ArrayList<String>) {
        _diaryIdList = diaryIdList
    }

    override fun initView() {
        checkIntentFrom()
        checkPart()
        initClickEditBtn()
        initPetImagePagerAdapter()
        initEmotionImageAdapter()
        setBackBtnClickListener()
        initDialog()
    }

    private fun checkPart() {
        when (MascotaSharedPreference.getPart()) {
            DiaryWriteActivity.PART1 -> observeResPetDiaryRead()
            else -> observeResPersonDiaryRead()
        }
    }

    private fun observeResPersonDiaryRead() {
        getResPersonDiaryRead()
        diaryViewModel.diaryReadPerson.observe(this) { resPersonDiaryRead ->
            resPersonDiaryRead.data.secondPartDiary.apply {
                with(binding) {
                    if (episode != 0) {
                        tvEpisode.text = makeEpisodeText(episode)
                    } else {
                        tvEpisode.visibility = View.INVISIBLE
                    }
                    tvTitle.text = title
                    tvDate.text = date
                    tvContent.text = contents
                    tvTogetherDay.visibility = View.INVISIBLE
                    Log.d("here", "arrive")
                    when {
                        diaryImg.isEmpty() -> {
                            isPictureExist = false
                            isPictures = false
                        }
                        diaryImg.size == 1 -> {
                            isPictureExist = true
                            isPictures = false
                        }
                        else -> {
                            isPictureExist = true
                            isPictures = true
                        }
                    }
                }
                petImagePagerAdapter.petImgUrlList = diaryImg

                val diaryEmoDataList = mutableListOf<DiaryEmoDataInfo>()

                var filteredList = listOf<ResPersonDiaryRead.Data.SecondPartDiary.Feeling>()
                for (kindIdx in 0..2) {
                    for (feelingIdx in 0..5) {
                        filteredList =
                            feelingList.filter { it.kind == kindIdx && it.feeling == feelingIdx }
                        Log.d("filteredList", "$filteredList")
                        if (filteredList.isNotEmpty()) {
                            val diaryImgsList = mutableListOf<String>()
                            for (element in filteredList) {
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


    private fun initDialog() {

        deleteDialog = DialogUtil.makeDialog(this@DiaryReadActivity)
        deleteDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.layout_mascota_dialog,
            null,
            false
        )

        DialogUtil.setDialog(deleteDialog, deleteDialogBinding.root)

        deleteDialogBinding.apply {
            tvQuit.setOnClickListener {
                deleteDialog.dismiss()
            }
            tvNext.setOnClickListener {
                deleteDialog.dismiss()
                Log.d("삭제 서버", "나중에 연동하기")

            }
        }
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
            showDeleteDialog()
            Log.d("다이얼로그 창", "나중에 추가해랑")

        }

        bottomSheet.show(supportFragmentManager.beginTransaction(), bottomSheet.tag)
    }


    private fun showDeleteDialog() {
        deleteDialogBinding.apply {
            tvTitle.text = getString(R.string.diary_delete)
            tvContent.text = getString(R.string.delete_story)
            tvNext.setTextColor(getColor(R.color.maco_orange))
            tvNext.setOnClickListener {
                finish()
            }
        }
        deleteDialog.show()
    }


    private fun observeResPetDiaryRead() {
        getResPetDiaryRead()
        diaryViewModel.diaryReadPet.observe(this) {
            it.data.petDiary.apply {
                with(binding) {
                    if (episode != 0) {
                        tvEpisode.text = makeEpisodeText(episode)
                    } else {
                        tvEpisode.visibility = View.INVISIBLE
                    }
                    tvTitle.text = title
                    tvDate.text = date
                    tvContent.text = contents
                    tvTogetherDay.visibility = View.VISIBLE
                    var color: Int = R.color.maco_orange
                    if(from == IS_BEST_MOMENT){
                        color = R.color.maco_blue
                    }
                    tvTogetherDay.text = tvTogetherDay.setTextPartialColor(
                        color,
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
                for (kindIdx in 0..2) {
                    for (feelingIdx in 0..5) {
                        filteredList =
                            feelingList.filter { it.kind == kindIdx && it.feeling == feelingIdx }
                        Log.d("filteredList", "$filteredList")
                        if (filteredList.isNotEmpty()) {
                            val diaryImgsList = mutableListOf<String>()
                            for (element in filteredList) {
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

    private fun getResPersonDiaryRead() {
        diaryViewModel.getPersonDiaryRead()
    }

    private fun postPetDiaryId(id: String) {
        diaryViewModel.postPetDiaryId(id)
    }

    private fun initPetImagePagerAdapter() {
        petImagePagerAdapter = PetImagePagerAdapter().apply {
            binding.vpPetImg.adapter = this
            pagerFrom = from
            binding.springDotsIndicator.setViewPager2(binding.vpPetImg)
            //petImgUrlList = petImageDataSource.getPetImageUrlData()
            //binding.springDotsIndicator.setViewPager2(binding.vpPetImg)
        }
    }

    private fun initEmotionImageAdapter() {
        emotionImageAdapter = EmotionImageAdapter().apply {
            binding.rvEmotion.adapter = this
        }
    }

    private fun setBackBtnClickListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun checkIntentFrom() {
        from = intent.getIntExtra("from", IS_CONTENT)
        intent.getStringArrayListExtra("petDiaryIdList")?.let { setDiaryIdList(it) }
        binding.apply {
            btnLeft.setBackgroundResource(R.drawable.selector_back_small)
            btnRight.setBackgroundResource(R.drawable.selector_next_small)
            clDiary.setBackgroundResource(R.drawable.ic_bg_grid)
            springDotsIndicator.dotsColor = R.color.maco_orange
            tvContent.setBackgroundResource(R.drawable.rectangle_orange_top_radius_3)
            ivLogo.setImageResource(R.drawable.ic_mascota_logo_orange)
        }
        when(from){
            IS_CONTENT -> {
                postPetDiaryId(_diaryIdList[0])
                with(binding) {
                    btnLeft.isEnabled = false
                    btnRight.isEnabled = false
                }
            }
            else -> {
                for (i in 0 until _diaryIdList.size) {
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
                if(from == IS_BEST_MOMENT) {
                    with(binding) {
                        btnLeft.setBackgroundResource(R.drawable.selector_back_small_blue)
                        btnRight.setBackgroundResource(R.drawable.selector_next_small_blue)
                        clDiary.setBackgroundResource(R.drawable.ic_grid_blue)
                        springDotsIndicator.dotsColor = R.color.maco_blue
                        tvContent.setBackgroundResource(R.drawable.rectangle_blue_top_radius_3)
                        ivLogo.setImageResource(R.drawable.ic_mascota_logo_blue)
                    }
                }

            }
        }
    }

    private fun setArrowBtnClickListener(id: String) {
        binding.btnLeft.setOnClickListener {
            postPetDiaryId(id)
            when(MascotaSharedPreference.getPart()){
                DiaryWriteActivity.PART1 -> getResPetDiaryRead()
                else -> getResPersonDiaryRead()
            }

        }
        binding.btnRight.setOnClickListener {
            postPetDiaryId(id)
            when(MascotaSharedPreference.getPart()){
                DiaryWriteActivity.PART1 -> getResPetDiaryRead()
                else -> getResPersonDiaryRead()
            }
        }
    }

    companion object {
        const val IS_CONTENT = 0
        const val IS_CALENDAR = 1
        const val IS_BEST_MOMENT = 2
    }
}

