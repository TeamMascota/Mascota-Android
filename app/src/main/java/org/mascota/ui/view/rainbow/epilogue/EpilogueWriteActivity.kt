package org.mascota.ui.view.rainbow.epilogue

import androidx.core.widget.addTextChangedListener
import org.mascota.R
import org.mascota.databinding.ActivityEpilogueWriteBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.util.StatusBarUtil.setStatusBarColor
import org.mascota.util.StringUtil.makePetLifeText
import org.mascota.util.StringUtil.makeTextLength

class EpilogueWriteActivity :
    BindingActivity<ActivityEpilogueWriteBinding>(R.layout.activity_epilogue_write) {
    private var isNotTitleEmpty = false
    private var isNotContentEmpty = false

    override fun initView() {
        setStatusBarColor(getColor(R.color.maco_blue))
        initPetName()
        initTextChangeEvent()
    }

    private fun initTextChangeEvent() {
        binding.apply {
            etEpilogueTitle.addTextChangedListener {
                isNotTitleEmpty = !it.isNullOrEmpty()
                tvEpilogueTitleSize.text = makeTextLength(etEpilogueTitle.length())
                enableCompleteButton()
            }
            etEpilogue.addTextChangedListener {
                isNotContentEmpty = !it.isNullOrEmpty()
                enableCompleteButton()
            }
        }
    }

    private fun initPetName() {
        binding.tvPet.text = makePetLifeText("코봉이")
    }

    private fun enableCompleteButton() {
        binding.btnComplete.isEnabled = isNotTitleEmpty && isNotContentEmpty
    }
}