package org.mascota.ui.view.rainbow.farewell

import org.mascota.R
import org.mascota.databinding.ActivityFarewellBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.adapter.MascotaViewPagerAdapter
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.FIRST_PAGE
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.SECOND_PAGE

class FarewellActivity : BindingActivity<ActivityFarewellBinding>(R.layout.activity_farewell) {
    private lateinit var mascotaViewPagerAdapter: MascotaViewPagerAdapter

    override fun initView() {
        initViewPager()
        initClickEvent()
    }

    private fun initViewPager() {
        mascotaViewPagerAdapter = MascotaViewPagerAdapter(this@FarewellActivity)
        binding.apply {
            with(mascotaViewPagerAdapter) {
                fragmentList = listOf(FarewellExplainFragment(), FarewellBookFragment())
                vpFarewell.adapter = this
                vpFarewell.isUserInputEnabled = false
            }
        }
    }

    private fun initClickEvent() {
        binding.apply {
            with(vpFarewell) {
                btnNext.setOnClickListener {
                    when(currentItem) {
                        FIRST_PAGE -> setCurrentItem(SECOND_PAGE, true)
                        SECOND_PAGE -> setCurrentItem(THIRD_PAGE, true)
                        else -> true
                    }
                }
            }
        }
    }

    companion object {
        const val THIRD_PAGE = 2
    }
}