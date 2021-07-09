package org.mascota.ui.view.rainbow.farewell

import android.app.Dialog
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import org.mascota.R
import org.mascota.databinding.ActivityFarewellBinding
import org.mascota.databinding.LayoutMascotaDialogBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.adapter.MascotaViewPagerAdapter
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.FIRST_PAGE
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.SECOND_PAGE
import org.mascota.util.DialogUtil

class FarewellActivity : BindingActivity<ActivityFarewellBinding>(R.layout.activity_farewell) {
    private lateinit var farewellViewPagerAdapter: MascotaViewPagerAdapter
    private lateinit var farewellFinishDialog : Dialog
    private lateinit var farewellFinishDialogBinding : LayoutMascotaDialogBinding

    override fun initView() {
        initDialogDataBinding()
        initDialog()
        initViewPager()
        initClickEvent()
    }

    private fun initViewPager() {
        farewellViewPagerAdapter = MascotaViewPagerAdapter(this@FarewellActivity)
        binding.apply {
            with(vpFarewell) {
                farewellViewPagerAdapter.apply {
                    fragmentList = listOf(FarewellExplainFragment(), FarewellBookFragment(),FarewellBestMoment())
                    adapter = this
                    isUserInputEnabled = false

                    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            isBackVisible = when (position) {
                                FIRST_PAGE -> false
                                else -> true
                            }
                        }
                    })
                }
            }
        }
    }

    private fun initClickEvent() {
        binding.apply {
            with(vpFarewell) {
                btnNext.setOnClickListener {
                    when (currentItem) {
                        FIRST_PAGE -> setCurrentItem(SECOND_PAGE, false)
                        SECOND_PAGE -> setCurrentItem(THIRD_PAGE, false)
                        else -> true
                    }
                }

                ibBack.setOnClickListener {
                    when (currentItem) {
                        SECOND_PAGE -> setCurrentItem(FIRST_PAGE, false)
                        THIRD_PAGE -> setCurrentItem(SECOND_PAGE, false)
                        else -> {
                            farewellFinishDialog.show()
                        }
                    }
                }
            }

            ibQuit.setOnClickListener {
                farewellFinishDialog.show()
            }

            farewellFinishDialogBinding.apply {
                clNext.setOnClickListener {
                    farewellFinishDialog.dismiss()
                    finish()
                }
                clQuit.setOnClickListener {
                    farewellFinishDialog.dismiss()
                }
            }
        }
    }

    private fun initDialog() {
        farewellFinishDialog = DialogUtil.makeDialog(this)

        DialogUtil.setDialog(farewellFinishDialog, farewellFinishDialogBinding.root)
    }

    private fun initDialogDataBinding() {
        farewellFinishDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.layout_mascota_dialog,
            null,
            false
        )

        farewellFinishDialogBinding.apply {
            tvContent.text = getString(R.string.finish_farewell)
            tvNext.text = getString(R.string.exit)
        }
    }

    override fun onBackPressed() {
        binding.apply {
            when (vpFarewell.currentItem) {
                FIRST_PAGE -> farewellFinishDialog.show()
                SECOND_PAGE -> vpFarewell.setCurrentItem(FIRST_PAGE, false)
                else -> vpFarewell.setCurrentItem(SECOND_PAGE, false)
            }
        }
    }

    companion object {
        const val THIRD_PAGE = 2
    }
}