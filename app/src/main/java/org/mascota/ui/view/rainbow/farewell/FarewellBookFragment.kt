package org.mascota.ui.view.rainbow.farewell

import androidx.core.view.isVisible
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mascota.R
import org.mascota.databinding.FragmentFarewellBookBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.viewmodel.RainbowViewModel
import org.mascota.util.AnimationUtil.getFadeInAnim
import org.mascota.util.ColorFilterUtil
import org.mascota.util.StringUtil
import org.mascota.util.StringUtil.makeAllText
import org.mascota.util.StringUtil.makeAuthorText
import org.mascota.util.StringUtil.makeDayText
import org.mascota.util.StringUtil.makeEpisodeText
import org.mascota.util.StringUtil.makeNowDay
import org.mascota.util.StringUtil.makeNowEpisode

class FarewellBookFragment :
    BindingFragment<FragmentFarewellBookBinding>(R.layout.fragment_farewell_book) {
    private val rainbowViewModel: RainbowViewModel by sharedViewModel()

    override fun initView() {
        setVisible(false)
        initColorFilter()
        // getPetInfo()
        // observePetInfo()
        getRainbowBookInfo()
        observeRainbowBookInfo()
    }

    private fun setVisible(value: Boolean) {
        binding.apply {
            tvWithDay.isVisible = value
            tvBook.isVisible = value
            clBook.isVisible = value
        }
    }


    private fun initColorFilter() {
        ColorFilterUtil.setImgFilter(binding.ivBookImg)
    }


    private fun observeRainbowBookInfo() {
        rainbowViewModel.rainbowBookInfo.observe(viewLifecycleOwner) {
            setVisible(true)
            binding.apply {
                with(requireContext().getFadeInAnim()) {
                    tvBook.startAnimation(this)
                    tvWithDay.startAnimation(this)
                    clBook.startAnimation(this)
                }

                with(it) {

                    val episodeText = makeAllText(makeEpisodeText(diaryCount))
                    val dayText = makeAllText(makeDayText(dayTogether))

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

                    tvTitle.text = bookInfo.title
                    tvWriter.text = makeAuthorText(bookInfo.author)

                    imgurl = bookInfo.bookImg

                }
            }
        }
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

    private fun getRainbowBookInfo() {
        rainbowViewModel.getRainbowBookInfo()
    }


}