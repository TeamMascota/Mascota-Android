package org.mascota.ui.view.calendar

import android.content.Intent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.FragmentCalendarBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.viewmodel.CalendarViewModel

class CalendarFragment : BindingFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {
    private val calendarViewModel: CalendarViewModel by viewModel()

    override fun initView() {
        initSwitchEvent()
        initClickEvent()
        initData()
        initCalendar()
        observeAuthorInfo()
        observeCurCalendar()
        observeDateItem()
    }

    private fun initSwitchEvent() {
        binding.apply {
            tvSwitchPart1.setOnClickListener {
                if(!it.isSelected)
                    it.isSelected = !it.isSelected
            }
            tvSwitchPart2.setOnClickListener {
                if(!it.isSelected)
                    it.isSelected = !it.isSelected
            }
        }
    }

    private fun initData() {
        calendarViewModel.getAuthorInfo()
        calendarViewModel.getDateItem()
    }

    private fun observeAuthorInfo() {
        calendarViewModel.authorInfo.observe(viewLifecycleOwner) {
            binding.authorInfoData = it
        }
    }

    private fun observeCurCalendar() {
        calendarViewModel.curCalendar.observe(viewLifecycleOwner) {
            binding.calendar.setCurCalendar(it)
        }
    }

    private fun observeDateItem() {
        calendarViewModel.dateItem.observe(viewLifecycleOwner) {
            binding.calendar.dateData = it
        }
    }

    private fun initCalendar() {
        binding.apply {
            calendarViewModel.setCalendar()

            with(calendar) {
                calendarViewModel.apply {
                    setMonthNextButtonClickListener { addMonth() }
                    setMonthPrevButtonClickListener { minusMonth() }
                    setYearNextButtonClickListener { addYear() }
                    setYearPrevButtonClickListener { minusYear() }
                }
            }
        }
    }

    private fun initClickEvent() {
        binding.clRecord.setOnClickListener {
            startDiaryWriteActivity()
        }
    }

    private fun startDiaryWriteActivity() {
        //startActivity(Intent(requireActivity(), DiaryWriteActivity::class.java))
    }

    companion object {
        const val DOG_ANGRY = 1
        const val DOG_BORING = 2
        const val DOG_JOY = 3
        const val DOG_LOVE = 4
        const val DOG_SAD = 5
        const val DOG_USUAL = 6
        const val EMPTY = 7
    }
}