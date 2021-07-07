package org.mascota.ui.view.content.edit

import android.app.AlertDialog
import android.view.LayoutInflater
import org.mascota.R
import org.mascota.databinding.ActivityContentEditBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.content.edit.adapter.ContentEditAdapter
import org.mascota.ui.view.content.edit.data.datasource.LocalContentEditDataSource

class ContentEditActivity : BindingActivity<ActivityContentEditBinding>(R.layout.activity_content_edit) {
    private lateinit var contentEditAdapter: ContentEditAdapter

    override fun initView() {
        initContentEditAdapter()
    }

    private fun initContentEditAdapter() {
        contentEditAdapter = ContentEditAdapter()
        binding.rvContent.adapter = contentEditAdapter
        val localContentEditDataSource = LocalContentEditDataSource()
        contentEditAdapter.contentEditList = localContentEditDataSource.getContentEditInfoData()
    }
}