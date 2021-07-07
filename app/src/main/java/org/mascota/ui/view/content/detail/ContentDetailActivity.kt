package org.mascota.ui.view.content.detail

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.ActivityContentDetailBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.content.detail.adapter.ContentDetailMonthAdapter
import org.mascota.ui.viewmodel.ContentDetailViewModel

class ContentDetailActivity : BindingActivity<ActivityContentDetailBinding>(R.layout.activity_content_detail) {
    private val contentDetailViewModel : ContentDetailViewModel by viewModel()
    private lateinit var contentDetailMonthAdapter: ContentDetailMonthAdapter

    override fun initView() {
        getContentMonthInfo()
        initContentDetailMonthAdapter()
        observeContentDetailMonthInfo()
    }

    private fun getContentMonthInfo() {
        contentDetailViewModel.getContentDetailMonthInfo()
    }

    private fun initContentDetailMonthAdapter() {
        contentDetailMonthAdapter = ContentDetailMonthAdapter()
        binding.rvContentMonth.adapter = contentDetailMonthAdapter
    }

    private fun observeContentDetailMonthInfo() {
        contentDetailViewModel.contentDetailMonth.observe(this) {
            contentDetailMonthAdapter.contentMonthList = it
        }
    }
}