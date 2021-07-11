package org.mascota.ui.view.diary

import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.data.local.MascotaSharedPreference.getPart
import org.mascota.databinding.ActivityDiaryWriteBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.adapter.MascotaViewPagerAdapter
import org.mascota.ui.view.diary.read.DiaryReadActivity
import org.mascota.ui.view.profile.ProfileCreateActivity
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.FIRST_PAGE
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.SECOND_PAGE
import org.mascota.ui.view.rainbow.farewell.FarewellActivity.Companion.THIRD_PAGE
import org.mascota.ui.viewmodel.DiaryViewModel
import org.mascota.util.AnimationUtil.setProgress100Animation
import org.mascota.util.AnimationUtil.setProgress50Animation
import org.mascota.util.EventObserver

class DiaryWriteActivity :
    BindingActivity<ActivityDiaryWriteBinding>(R.layout.activity_diary_write) {
    private val diaryViewModel: DiaryViewModel by viewModel()
    private lateinit var diaryWriteViewPagerAdapter: MascotaViewPagerAdapter

    override fun initView() {
        initViewPager()
        initClickEvent()
        observeBtnEnableEvent()
    }

    private fun initViewPager() {
        diaryWriteViewPagerAdapter = MascotaViewPagerAdapter(this)
        binding.apply {
            with(diaryWriteViewPagerAdapter) {
                fragmentList = when(getPart()) {
                    PART1 -> listOf(DiaryEmotionFragment(), DiaryDetailWriteFragment())
                    else -> listOf(DiaryWriterEmotionFragment(), DiarySolutionFragment(), DiaryDetailWriteFragment())
                }

                vpDiaryWrite.adapter = this
                vpDiaryWrite.isUserInputEnabled = false
            }
        }
    }

    private fun initClickEvent() {
        binding.apply {
            with(vpDiaryWrite) {
                btnNext.setOnClickListener {
                    when (currentItem) {
                        FIRST_PAGE -> setCurrentItem(SECOND_PAGE, true)
                        SECOND_PAGE -> {
                            if(getPart() == PART2)
                                setCurrentItem(THIRD_PAGE, true)
                            else
                                startDiaryReadActivity()
                        }
                        else ->
                    }
                }

                btnBack.setOnClickListener {
                    when (currentItem) {
                        FIRST_PAGE -> finish()
                        else -> setCurrentItem(FIRST_PAGE, true)
                    }
                }

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        tvPage.text = (currentItem + 1).toString()

                        when (position) {
                            FIRST_PAGE -> {
                                setProgress50Animation(pbDiaryWrite)
                                binding.btnNext.text = getString(R.string.next)
                            }
                            else -> {
                                setProgress100Animation(pbDiaryWrite)
                                binding.btnNext.text = getString(R.string.write_complete)
                            }
                        }
                    }
                })
            }
        }
    }

    private fun observeBtnEnableEvent() {
        diaryViewModel.nextBtnEnableEvent.observe(this, EventObserver{
            binding.btnNext.isEnabled = it
        })
    }

    private fun startDiaryReadActivity() {
        startActivity(Intent(this, DiaryReadActivity::class.java))
    }

    override fun onBackPressed() {
        binding.apply {
            when (vpDiaryWrite.currentItem) {
                FIRST_PAGE -> finish()
                else -> vpDiaryWrite.setCurrentItem(FIRST_PAGE, true)
            }
        }
    }

    companion object {
        const val PART1 = 1
        const val PART2 = 2
    }
}