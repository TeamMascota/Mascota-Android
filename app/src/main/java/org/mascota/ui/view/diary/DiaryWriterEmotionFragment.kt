package org.mascota.ui.view.diary

import android.view.View
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mascota.R
import org.mascota.databinding.FragmentDiaryWriterEmotionBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.viewmodel.DiaryViewModel
import org.mascota.util.AnimationUtil.getFadeInAnim

class DiaryWriterEmotionFragment :
    BindingFragment<FragmentDiaryWriterEmotionBinding>(R.layout.fragment_diary_writer_emotion) {
    private val diaryViewModel: DiaryViewModel by sharedViewModel()

    override fun initView() {
        initVisible()
        initClickEvent()
    }

    private fun initVisible() {
        binding.apply {
            isAcceptVisible = false
            isDenyVisible = false
            isSadVisible = false
            isAngryVisible = false
            isLossVisible = false
            isRegretVisible = false
        }
    }

    private fun initClickEvent() {
        binding.apply {
            with(requireContext()) {
                clDeny.setOnClickListener {
                    if (!requireNotNull(isDenyVisible)) {
                        isDenyVisible = true
                        tvDenyExplain.startAnimation(getFadeInAnim())
                        setClickEvent(clDeny)
                        diaryViewModel.postBtnEnable(true)
                    }
                }
                clAngry.setOnClickListener {
                    if (!requireNotNull(isAngryVisible)) {
                        isAngryVisible = true
                        tvExplainAngry.startAnimation(getFadeInAnim())
                        setClickEvent(clAngry)
                        diaryViewModel.postBtnEnable(true)
                    }
                }
                clRegret.setOnClickListener {
                    if (!requireNotNull(isRegretVisible)) {
                        isRegretVisible = true
                        tvExplainRegret.startAnimation(getFadeInAnim())
                        setClickEvent(clRegret)
                        diaryViewModel.postBtnEnable(true)
                    }
                }
                clLoss.setOnClickListener {
                    if (!requireNotNull(isLossVisible)) {
                        isLossVisible = true
                        tvExplainLoss.startAnimation(getFadeInAnim())
                        setClickEvent(clLoss)
                        diaryViewModel.postBtnEnable(true)
                    }
                }
                clSad.setOnClickListener {
                    if (!requireNotNull(isSadVisible)) {
                        isSadVisible = true
                        tvExplainSad.startAnimation(getFadeInAnim())
                        setClickEvent(clSad)
                        diaryViewModel.postBtnEnable(true)
                    }
                }
                clAccept.setOnClickListener {
                    if (!requireNotNull(isAcceptVisible)) {
                        isAcceptVisible = true
                        tvExplainAccept.startAnimation(getFadeInAnim())
                        setClickEvent(clAccept)
                        diaryViewModel.postBtnEnable(true)
                    }
                }
            }
        }
    }

    private fun setClickEvent(view: View) {
        binding.apply {
            when (view) {
                clDeny -> {
                    tvDenyTitle.isSelected = true
                    ivEmoDeny.isSelected = true
                    initReleaseEvent(view)
                }
                clAngry -> {
                    tvTitleAngry.isSelected = true
                    ivEmoAngry.isSelected = true
                    initReleaseEvent(view)
                }
                clRegret -> {
                    tvTitleRegret.isSelected = true
                    ivEmoRegret.isSelected = true
                    initReleaseEvent(view)
                }
                clLoss -> {
                    tvTitleLoss.isSelected = true
                    ivLoss.isSelected = true
                    initReleaseEvent(view)
                }
                clSad -> {
                    tvTitleSad.isSelected = true
                    ivSad.isSelected = true
                    initReleaseEvent(view)
                }
                clAccept -> {
                    tvTitleAccept.isSelected = true
                    ivAccept.isSelected = true
                    initReleaseEvent(view)
                }
            }
        }
    }

    private fun initReleaseEvent(view: View) {
        binding.apply {
            (view == clAngry).apply {
                tvTitleAngry.isSelected = this
                ivEmoAngry.isSelected = this
                if(!this)
                    tvExplainAngry.clearAnimation()
                isAngryVisible = this
            }
            (view == clDeny).apply {
                tvDenyTitle.isSelected = this
                ivEmoDeny.isSelected = this
                if(!this)
                    tvDenyExplain.clearAnimation()
                isDenyVisible = this
            }
            (view == clSad).apply {
                tvTitleSad.isSelected = this
                ivSad.isSelected = this
                if(!this)
                    tvExplainSad.clearAnimation()
                isSadVisible = this
            }
            (view == clAccept).apply {
                tvTitleAccept.isSelected = this
                ivAccept.isSelected = this
                if(!this)
                    tvExplainAccept.clearAnimation()
                isAcceptVisible = this
            }
            (view == clRegret).apply {
                tvTitleRegret.isSelected = this
                ivEmoRegret.isSelected = this
                if(!this)
                    tvExplainRegret.clearAnimation()
                isRegretVisible = this
            }
            (view == clLoss).apply {
                tvTitleLoss.isSelected = this
                ivLoss.isSelected = this
                if(!this)
                    tvExplainLoss.clearAnimation()
                isLossVisible = this
            }
        }
    }
}