package org.mascota.ui.view.diary

import android.content.Intent
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.data.local.MascotaSharedPreference.getPart
import org.mascota.data.local.MascotaSharedPreference.setPart
import org.mascota.databinding.ActivityDiaryWriteBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.adapter.MascotaViewPagerAdapter
import org.mascota.ui.view.diary.read.DiaryReadActivity
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.FIRST_PAGE
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.SECOND_PAGE
import org.mascota.ui.view.rainbow.farewell.FarewellActivity.Companion.THIRD_PAGE
import org.mascota.ui.viewmodel.DiaryViewModel
import org.mascota.util.AnimationUtil.getFadeInAnim
import org.mascota.util.AnimationUtil.setProgress100Animation
import org.mascota.util.AnimationUtil.setProgress50Animation
import org.mascota.util.EventObserver
import org.mascota.util.StatusBarUtil.setStatusBarColor

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
                fragmentList = when (getPart()) {
                    PART1 -> listOf(DiaryEmotionFragment(), DiaryDetailWriteFragment())
                    else -> listOf(
                        DiaryWriterEmotionFragment(),
                        DiaryDetailWriteFragment(),
                        DiarySolutionFragment()
                    )
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
                        FIRST_PAGE -> {
                            if (getPart() == PART1)
                                setCurrentItem(SECOND_PAGE, true)
                            else
                                setCurrentItem(THIRD_PAGE, false)
                        }
                        SECOND_PAGE ->
                            startDiaryReadActivity()
                        else ->
                            setCurrentItem(SECOND_PAGE, false)
                    }
                }

                btnBack.setOnClickListener {
                    when (currentItem) {
                        FIRST_PAGE -> finish()
                        SECOND_PAGE -> setCurrentItem(FIRST_PAGE, true)
                        else -> setCurrentItem(FIRST_PAGE, false)
                    }
                }

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        tvPage.text = (currentItem + 1).toString()

                        when (position) {
                            FIRST_PAGE -> {
                                setDefaultTopBar()
                                setProgress50Animation(pbDiaryWrite)
                                btnNext.text = getString(R.string.next)
                            }
                            SECOND_PAGE -> {
                                setDefaultTopBar()
                                setProgress100Animation(pbDiaryWrite)
                                binding.btnNext.text = getString(R.string.write_complete)
                            }
                            else -> setSolutionTopBar()
                        }
                    }
                })
            }
        }
    }

    private fun observeBtnEnableEvent() {
        diaryViewModel.nextBtnEnableEvent.observe(this, EventObserver {
            binding.btnNext.isEnabled = it
        })
    }

    private fun startDiaryReadActivity() {
        startActivity(Intent(this, DiaryReadActivity::class.java))
    }

    private fun setDefaultTopBar() {
        setStatusBarColor(getColor(R.color.maco_ivory))
        binding.apply {
            isWhite = false
            tvTitle.text = getString(R.string.story_write)
            clTopBar.setBackgroundColor(getColor(R.color.maco_ivory))
            tvPage.visibility = View.VISIBLE
            tvTotalPage.visibility = View.VISIBLE
            pbDiaryWrite.visibility = View.VISIBLE
        }
    }

    private fun setSolutionTopBar() {
        setStatusBarColor(getColor(R.color.maco_orange))
        binding.apply {
            isWhite = true
            tvTitle.text = getString(R.string.help_talk)
            tvPage.visibility = View.INVISIBLE
            tvTotalPage.visibility = View.INVISIBLE
            pbDiaryWrite.visibility = View.INVISIBLE
            clTopBar.setBackgroundColor(getColor(R.color.maco_orange))
            clTopBar.startAnimation(getFadeInAnim())
        }
    }

    override fun onBackPressed() {
        binding.apply {
            when (vpDiaryWrite.currentItem) {
                FIRST_PAGE -> finish()
                SECOND_PAGE -> vpDiaryWrite.setCurrentItem(FIRST_PAGE, true)
                else -> vpDiaryWrite.setCurrentItem(FIRST_PAGE, false)
            }
        }
    }

    companion object {
        const val PART1 = 1
        const val PART2 = 2
    }
}