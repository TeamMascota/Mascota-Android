package org.mascota.ui.view.profile

import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.ActivityProfileCreateBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.adapter.MascotaViewPagerAdapter
import org.mascota.ui.viewmodel.ProfileViewModel
import org.mascota.util.AnimationUtil.setProgress100Animation
import org.mascota.util.AnimationUtil.setProgress50Animation
import org.mascota.util.EventObserver

class ProfileCreateActivity :
    BindingActivity<ActivityProfileCreateBinding>(R.layout.activity_profile_create) {
    private lateinit var mascotaViewPagerAdapter: MascotaViewPagerAdapter
    private val profileViewModel: ProfileViewModel by viewModel()

    override fun initView() {
        initViewPager()
        initClickEvent()
        observeBtnEnable()
    }

    private fun initViewPager() {
        mascotaViewPagerAdapter = MascotaViewPagerAdapter(this@ProfileCreateActivity)
        binding.apply {
            with(mascotaViewPagerAdapter) {
                fragmentList = listOf(ProfileCreatePetFragment(), ProfileCreateBookFragment())
                vpProfile.adapter = this
                vpProfile.isUserInputEnabled = false
            }
        }
    }

    private fun initClickEvent() {
        binding.apply {
            with(vpProfile) {
                btnNext.setOnClickListener {
                    when (currentItem) {
                        FIRST_PAGE -> {
                            profileViewModel.postProfilePet()
                            setCurrentItem(SECOND_PAGE, true)
                        }
                        else -> startBookCompleteActivity()
                    }
                }

                ibBack.setOnClickListener {
                    when (vpProfile.currentItem) {
                        FIRST_PAGE -> finish()
                        else -> setCurrentItem(FIRST_PAGE, true)
                    }
                }

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        tvPage.text = (currentItem + 1).toString()

                        when (position) {
                            FIRST_PAGE -> {
                                tvTitle.text = getString(R.string.hero_register)
                                setProgress50Animation(pbProfile)
                            }
                            else -> {
                                tvTitle.text = getString(R.string.book_register)
                                setProgress100Animation(pbProfile)
                            }
                        }
                    }
                })
            }
        }
    }

    private fun observeBtnEnable() {
        profileViewModel.nextBtnEnableEvent.observe(this, EventObserver {
            binding.btnNext.isEnabled = it
        })
    }


    private fun startBookCompleteActivity() {
        startActivity(
            Intent(this@ProfileCreateActivity, BookCompleteActivity::class.java).apply {
                putExtra(WRITER, profileViewModel.writer)
                putExtra(TITLE, profileViewModel.title)
                putExtra(IMG_URI, profileViewModel.imgUri.toString())
                putExtra(PET_NUM, profileViewModel.petInfoDataList.size.toString())
            }
        )
    }

    override fun onBackPressed() {
        binding.apply {
            when (vpProfile.currentItem) {
                FIRST_PAGE -> finish()
                else -> vpProfile.setCurrentItem(FIRST_PAGE, true)
            }
        }
    }

    companion object {
        const val ONE_SECOND = 1000L
        const val PROGRESS_SIZE_100 = 100
        const val PROGRESS_SIZE_50 = 50
        const val FIRST_PAGE = 0
        const val SECOND_PAGE = 1
        const val WRITER = "writer"
        const val TITLE = "title"
        const val PET_NUM = "pet_num"
        const val IMG_URI = "URI"
    }
}