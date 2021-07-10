package org.mascota.ui.view.diary.detail

import org.mascota.R
import org.mascota.databinding.ActivityEpilogueDetailBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.util.StatusBarUtil.setStatusBarColor

class EpilogueDetailActivity : BindingActivity<ActivityEpilogueDetailBinding>(R.layout.activity_epilogue_detail) {
    override fun initView() {
        setStatusBarColor(getColor(R.color.maco_orange))
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