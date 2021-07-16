package org.mascota.ui.view.rainbow.epilogue

import android.content.Intent
import android.util.Log
import androidx.core.widget.addTextChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.data.local.MascotaSharedPreference.setPart
import org.mascota.databinding.ActivityEpilogueWriteBinding
import org.mascota.ui.MainActivity
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.diary.DiaryWriteActivity.Companion.PART2
import org.mascota.ui.viewmodel.RainbowViewModel
import org.mascota.util.EventObserver
import org.mascota.util.StatusBarUtil.setStatusBarColor
import org.mascota.util.StringUtil.makeTextLength

class EpilogueWriteActivity :
    BindingActivity<ActivityEpilogueWriteBinding>(R.layout.activity_epilogue_write) {
    private var isNotTitleEmpty = false
    private var isNotContentEmpty = false
    private val rainbowViewModel: RainbowViewModel by viewModel()


    override fun initView() {
        observeBtnEnable()
        setStatusBarColor(getColor(R.color.maco_blue))
        getPetName()
        clickBtn()
        initTextChangeEvent()
        observeEp()
    }


    private fun getPetName(){
        rainbowViewModel.getPetName()
        rainbowViewModel.petName.observe(this){

            binding.apply {
                tvPet.text = it.data.name
                tvPet2.text = it.data.name
                petname  = it
                val hint = it.data.name + getString(R.string.end_story)
                etEpilogue.hint = hint
            }
        }
    }

    private fun initTextChangeEvent() {
        binding.apply {
            etEpilogueTitle.addTextChangedListener {
                isNotTitleEmpty = !it.isNullOrEmpty()
                tvEpilogueTitleSize.text = makeTextLength(etEpilogueTitle.length())
                enableCompleteButton()
                rainbowViewModel.postTitle(binding.etEpilogueTitle.text.toString())
            }
            etEpilogue.addTextChangedListener {
                isNotContentEmpty = !it.isNullOrEmpty()
                enableCompleteButton()
                rainbowViewModel.postContent(binding.etEpilogue.text.toString())
            }
        }
    }

    private fun observeBtnEnable() {
        rainbowViewModel.nextBtnEnaleEvent.observe(this, EventObserver{
            binding.btnComplete.isEnabled = it
        })
    }

    private fun enableCompleteButton() {
        binding.btnComplete.isEnabled = isNotTitleEmpty && isNotContentEmpty
    }

    private fun observeEp() {
        rainbowViewModel.epliEvent.observe(this, EventObserver {
            when(it) {
                true -> {

                }
                false -> Log.d("이동","못함")
            }

        })
    }

    private fun clickBtn(){
        binding.btnComplete.setOnClickListener {
            rainbowViewModel.postEpilogue()

            finishAffinity()
            setPart(PART2)
            startActivity(Intent(this@EpilogueWriteActivity, MainActivity::class.java))
        }
    }

    companion object {
        const val IS_EPILOGUE_WRITE = "is_epilogue_write"
    }
}