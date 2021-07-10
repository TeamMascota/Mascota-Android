package org.mascota.ui.view.diary.detail

import org.mascota.R
import org.mascota.databinding.ActivityEpilogueDetailBinding
import org.mascota.ui.base.BindingActivity

class EpilogueDetailActivity : BindingActivity<ActivityEpilogueDetailBinding>(R.layout.activity_epilogue_write) {
    override fun initView() {
        initText()
    }

    private fun initText() {
        binding.apply {
            tvWriter.text = getString(R.string.soeun)
            tvTitle.text = getString(R.string.thanks_cobong)
            tvContent.text = getString(R.string.epilogue_content)
        }
    }
}