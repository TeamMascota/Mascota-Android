package org.mascota.ui.view.content.detail

import android.content.Intent
import android.util.Log
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.ActivityContentDetailBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.content.detail.adapter.ContentDetailMonthAdapter
import org.mascota.ui.view.diary.read.DiaryReadActivity
import org.mascota.ui.viewmodel.ContentViewModel
import org.mascota.util.StringUtil.makeChapterText

class ContentDetailActivity :
    BindingActivity<ActivityContentDetailBinding>(R.layout.activity_content_detail) {
    private val contentViewModel: ContentViewModel by viewModel()
    private lateinit var contentDetailMonthAdapter: ContentDetailMonthAdapter

    override fun initView() {
        getResContentDetail()
        initContentDetailMonthAdapter()
        observeResContentDetail()
        setBackBtnClickListener()
    }

    private fun getResContentDetail() {
        contentViewModel.getContentDetail(intent.getStringExtra("chapterId").toString())
    }

    private fun observeResContentDetail() {
        contentViewModel.resContentDetail.observe(this) {
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