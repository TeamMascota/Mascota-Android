package org.mascota.ui.view.content.detail

import android.app.Dialog
import android.content.Intent
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.data.local.MascotaSharedPreference
import org.mascota.data.remote.model.response.content.ResContentDetail
import org.mascota.data.remote.model.response.content.ResPart2ContentDetail
import org.mascota.databinding.ActivityContentDetailBinding
import org.mascota.databinding.LayoutLoadingBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.content.detail.adapter.ContentDetailMonthAdapter
import org.mascota.ui.view.diary.DiaryWriteActivity
import org.mascota.ui.view.diary.read.DiaryReadActivity
import org.mascota.ui.viewmodel.ContentViewModel
import org.mascota.util.DialogUtil
import org.mascota.util.StringUtil.makeChapterText
import org.mascota.util.StringUtil.makeDayText

class ContentDetailActivity :
    BindingActivity<ActivityContentDetailBinding>(R.layout.activity_content_detail) {
    private val contentViewModel: ContentViewModel by viewModel()
    private lateinit var contentDetailMonthAdapter: ContentDetailMonthAdapter
    private lateinit var loadingDialog: Dialog
    private lateinit var loadingDialogBinding: LayoutLoadingBinding

    override fun initView() {
        initLoadingDialog()
        checkPart()
        observeResContentDetail()
        observeResPart2ContentDetail()
        initContentDetailMonthAdapter()
        setBackBtnClickListener()
    }

    private fun initLoadingDialog() {
        loadingDialog = DialogUtil.makeLoadingDialog(this)

        loadingDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.layout_loading,
            null,
            false
        )

        DialogUtil.setLoadingDialog(loadingDialog, loadingDialogBinding.root)

        loadingDialog.show()
    }

    private fun checkPart() {
        when (MascotaSharedPreference.getPart()) {
            DiaryWriteActivity.PART1 -> observeResContentDetail()
            else -> observeResPart2ContentDetail()
        }
    }

    private fun getResContentDetail() {
        contentViewModel.getContentDetail(intent.getStringExtra("chapterId").toString())
    }

    private fun getResPart2ContentDetail() {
        contentViewModel.getPart2ContentDetail(intent.getStringExtra("chapterId").toString())
    }

    private fun observeResPart2ContentDetail() {
        getResPart2ContentDetail()
        contentViewModel.resPart2ContentDetail.observe(this){
            loadingDialog.dismiss()
            it.data.apply {
                contentDetailMonthAdapter.contentMonthList = changeResContentDetail(diaryiesOfMonth)
                binding.tvChapter.text = makeChapterText(chapter)
                binding.tvTitle.text = chapterTitle
            }
        }
    }

    private fun changeResContentDetail(data: List<ResPart2ContentDetail.Data.Monthly>)
    : List<ResContentDetail.Data.PetChapterDiary.Monthly> {
        return data.map {
            changeMonthly(it)
        }
    }

    private fun changeMonthly(data: ResPart2ContentDetail.Data.Monthly) : ResContentDetail.Data.PetChapterDiary.Monthly{
        return ResContentDetail.Data.PetChapterDiary.Monthly(
            data.diaryCountOfTableContents, data.month, changeDiaryList(data.diaries)
        )
    }

    private fun changeDiaryList(data: List<ResPart2ContentDetail.Data.Monthly.Diary>)
    : List<ResContentDetail.Data.PetChapterDiary.Monthly.Diary>{
        return data.map { changeResContentDetailDiary(it)}
    }

    private fun changeResContentDetailDiary(data: ResPart2ContentDetail.Data.Monthly.Diary)
    : ResContentDetail.Data.PetChapterDiary.Monthly.Diary {
        data.apply {
            return ResContentDetail.Data.PetChapterDiary.Monthly.Diary(
                diaryId, title, contents, 0, img, 0, feeling, makeDayText(days), dayOfWeek + getString(R.string.week_day), kind
            )
        }
    }

    private fun observeResContentDetail() {
        getResContentDetail()
        contentViewModel.resContentDetail.observe(this) {
            loadingDialog.dismiss()
            it.data.petChapterDiary.apply {
                contentDetailMonthAdapter.contentMonthList = monthly
                binding.tvChapter.text = makeChapterText(chapter)
                binding.tvTitle.text = chapterTitle
            }

        }
    }

    private fun initContentDetailMonthAdapter() {
        contentDetailMonthAdapter = ContentDetailMonthAdapter()
        binding.rvContentMonth.adapter = contentDetailMonthAdapter
        contentDetailMonthAdapter.setNavigateDiaryReadListener {
            val intent = Intent(this, DiaryReadActivity::class.java)
            intent.putStringArrayListExtra("petDiaryIdList", arrayListOf(it))
            intent.putExtra("from", DiaryReadActivity.IS_CONTENT)
            startActivity(intent)
        }
    }

    private fun setBackBtnClickListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}