package org.mascota.ui.view.rainbow.epilogue

import androidx.core.widget.addTextChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.ActivityEpilogueWriteBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.viewmodel.RainbowViewModel
import org.mascota.util.StatusBarUtil.setStatusBarColor
import org.mascota.util.StringUtil.makePetLifeText
import org.mascota.util.StringUtil.makeTextLength

class EpilogueWriteActivity :
    BindingActivity<ActivityEpilogueWriteBinding>(R.layout.activity_epilogue_write) {
    private var isNotTitleEmpty = false
    private var isNotContentEmpty = false

    private val rainbowViewModel: RainbowViewModel by viewModel()

    override fun initView() {
        setStatusBarColor(getColor(R.color.maco_blue))
      //  initPetName()
        initTextChangeEvent()
        getPetName()
    }


    private fun showHint(){

        binding.etEpilogue.setText(R.string.end_story)
    }

    private fun getPetName(){
        rainbowViewModel.getPetName()
        rainbowViewModel.petName.observe(this){
            binding.tvPet.text = it.data.name
            binding.tvPet2.text = it.data.name
           binding.petname  = it

            val hint = it.data.name + getString(R.string.end_story)
            binding.etEpilogue.hint = hint
                    //petnameinfo.data.name = it.data.name
        }
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