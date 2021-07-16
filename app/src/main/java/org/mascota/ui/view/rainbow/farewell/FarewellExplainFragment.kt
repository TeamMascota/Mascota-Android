package org.mascota.ui.view.rainbow.farewell

import androidx.core.view.isVisible
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.FragmentFarewellExplainBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.viewmodel.RainbowViewModel
import org.mascota.util.AnimationUtil.getFadeInAnim
import org.mascota.util.StringUtil.makeBestMomentText
import org.mascota.util.StringUtil.makeEpisodeText
import org.mascota.util.StringUtil.makePetInfoText
import org.mascota.util.StringUtil.makeWithStoryText
import org.mascota.util.StringUtil.setTextPartialBold

class FarewellExplainFragment :
    BindingFragment<FragmentFarewellExplainBinding>(R.layout.fragment_farewell_explain) {
    private val rainbowViewModel: RainbowViewModel by sharedViewModel()

    override fun initView() {
        putRainbowContent()
        observeRainbowContent()

    // getPetInfo()
      //  observePetInfo()
    }

    private fun getPetInfo() {
        rainbowViewModel.getPetInfo()
    }

    private fun setImgVisible() {
        binding.ivIllust.apply {
            isVisible = true
            startAnimation(requireContext().getFadeInAnim())
        }
    }

    private fun putRainbowContent(){
        rainbowViewModel.putRainbowContent()
    }

    private fun observeRainbowContent(){
        rainbowViewModel.rainbowContent.observe(viewLifecycleOwner){
            binding.apply {
                with(it){
                    setImgVisible()
                    tvInStory.text = data.partingRainbowBridge.contents
                    tvInStory.startAnimation(requireContext().getFadeInAnim())
                }
            }
        }
    }

    private fun observePetInfo() {
        rainbowViewModel.petInfo.observe(viewLifecycleOwner) {
            binding.apply {
                with(it) {
                    val episodeText = makeEpisodeText(episode)
                    tvInStory.text = setTextPartialBold(
                        10,
                        10 + episodeText.length,
                        makeWithStoryText(episodeText)
                    )
                    when (species) {
                        DOG -> tvAnimal.text = makePetInfoText(name, getString(R.string.dog))
                        else -> tvAnimal.text = makePetInfoText(name, getString(R.string.cat))
                    }
                    tvBestMoment.text = makeBestMomentText(name)
                }
            }
        }
    }

    companion object {
        const val DOG = 1
        const val CAT = 2
    }
}