package org.mascota.ui.view.rainbow.farewell

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mascota.R
import org.mascota.databinding.FragmentFarewellBookBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.viewmodel.RainbowViewModel
import org.mascota.util.StringUtil
import org.mascota.util.StringUtil.makeAllText
import org.mascota.util.StringUtil.makeAuthorText
import org.mascota.util.StringUtil.makeDayText
import org.mascota.util.StringUtil.makeEpisodeText
import org.mascota.util.StringUtil.makeNowDay
import org.mascota.util.StringUtil.makeNowEpisode
import org.mascota.util.extension.getColor
import org.mascota.util.extension.setImgBackgroundFilter

class FarewellBookFragment :
    BindingFragment<FragmentFarewellBookBinding>(R.layout.fragment_farewell_book) {
    private val rainbowViewModel: RainbowViewModel by sharedViewModel()

    override fun initView() {
        initColorFilter()
        getPetInfo()
        observePetInfo()
    }


    private fun initColorFilter() {
        binding.ivBookImg.setImgBackgroundFilter()
    }

    private fun observePetInfo() {
        rainbowViewModel.petInfo.observe(viewLifecycleOwner) {
            binding.apply {
                with(it) {
                    val episodeText = makeAllText(makeEpisodeText(episode))
                    val dayText = makeAllText(makeDayText(day))
                    tvBook.text = StringUtil.setTextPartialBold(
                        10,
                        10 + episodeText.length,
                        makeNowEpisode(episodeText)
                    )

                    tvWithDay.text = StringUtil.setTextPartialBold(
                        9,
                        9 + dayText.length,
                        makeNowDay(dayText)
                    )

                    tvTitle.text = title
                    tvWriter.text = makeAuthorText(author)
                }
            }
        }
    }

    private fun getPetInfo() {
        rainbowViewModel.getPetInfo()
    }
}