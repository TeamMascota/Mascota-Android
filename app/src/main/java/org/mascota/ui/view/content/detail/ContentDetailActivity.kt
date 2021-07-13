package org.mascota.ui.view.content.detail

import android.content.Intent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.ActivityContentDetailBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.content.detail.adapter.ContentDetailMonthAdapter
import org.mascota.ui.viewmodel.ContentViewModel
import org.mascota.ui.view.diary.read.DiaryReadActivity

class ContentDetailActivity : BindingActivity<ActivityContentDetailBinding>(R.layout.activity_content_detail) {
    private val contentViewModel : ContentViewModel by viewModel()
    private lateinit var contentDetailMonthAdapter: ContentDetailMonthAdapter

    override fun initView() {
        getContentMonthInfo()
        initContentDetailMonthAdapter()
        observeContentDetailMonthInfo()
        setBackBtnClickListener()
    }

    private fun getContentMonthInfo() {
        contentViewModel.getContentDetailMonthInfo()
    }

    private fun initContentDetailMonthAdapter() {
        contentDetailMonthAdapter = ContentDetailMonthAdapter()
        binding.rvContentMonth.adapter = contentDetailMonthAdapter
        contentDetailMonthAdapter.setNavigateDiaryReadListener {
            startActivity(Intent(this, DiaryReadActivity::class.java))
        }
    }

    private fun observeContentDetailMonthInfo() {
        contentViewModel.contentDetailMonth.observe(this) {
            contentDetailMonthAdapter.contentMonthList = it
        }
    }

    private fun setBackBtnClickListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}