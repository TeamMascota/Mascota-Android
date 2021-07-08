package org.mascota.ui.view.content.edit

import org.mascota.R
import org.mascota.databinding.ActivityContentEditBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.content.edit.adapter.ContentEditAdapter
import org.mascota.ui.view.content.edit.data.datasource.LocalContentEditDataSource

class ContentEditActivity : BindingActivity<ActivityContentEditBinding>(R.layout.activity_content_edit) {

    override fun initView() {
        initContentEditAdapter()
    }

    private fun initContentEditAdapter() {
        ContentEditAdapter().apply {
            binding.rvContent.adapter = this
            val localContentEditDataSource = LocalContentEditDataSource()
            contentEditList = localContentEditDataSource.getContentEditInfoData()
        }
    }
}