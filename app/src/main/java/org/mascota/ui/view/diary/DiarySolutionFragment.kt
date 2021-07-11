package org.mascota.ui.view.diary

import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mascota.R
import org.mascota.databinding.FragmentDiarySolutionBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.viewmodel.DiaryViewModel
import org.mascota.util.AnimationUtil.getFadeInAnim
import org.mascota.util.BindingAdapter.ACCEPT
import org.mascota.util.BindingAdapter.ANGRY
import org.mascota.util.BindingAdapter.DENY
import org.mascota.util.BindingAdapter.LOSS
import org.mascota.util.BindingAdapter.REGRET
import org.mascota.util.BindingAdapter.SAD
import org.mascota.util.StringUtil.makeAcceptText
import org.mascota.util.StringUtil.makeDenyText
import org.mascota.util.StringUtil.makeLossText
import org.mascota.util.StringUtil.makeRegretText

class DiarySolutionFragment :
    BindingFragment<FragmentDiarySolutionBinding>(R.layout.fragment_diary_solution) {
    private val diaryViewModel: DiaryViewModel by sharedViewModel()

    override fun initView() {
        observeEmotion()
    }

    private fun observeEmotion() {
        diaryViewModel.selectedEmotion.observe(this) {
            binding.apply {
                emo = it
                when (it) {
                    SAD -> setText(getString(R.string.depressed), getString(R.string.sad_solution))
                    DENY -> setText(getString(R.string.deny), makeDenyText(getString(R.string.cobong)))
                    ANGRY -> setText(getString(R.string.rage), getString(R.string.angry_solution))
                    REGRET -> setText(getString(R.string.regret), makeRegretText(getString(R.string.cobong)))
                    LOSS -> setText(getString(R.string.loss), makeLossText(getString(R.string.cobong)))
                    ACCEPT -> setText(getString(R.string.accept), makeAcceptText(getString(R.string.cobong)))
                }
            }
        }
    }

    private fun setText(emo: String, emoExplain:String) {
        binding.apply {
            tvEmo.text = emo
            tvExplain.text = emoExplain
        }
    }

    private fun setAnim() {
        binding.clSolution.startAnimation(requireContext().getFadeInAnim())
    }

    override fun onResume() {
        super.onResume()
        setAnim()
    }
}