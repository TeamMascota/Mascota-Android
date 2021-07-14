package org.mascota.ui.view.calendar

import android.content.Intent
import android.util.Log
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.FragmentCalendarBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.diary.DiaryWriteActivity
import org.mascota.ui.viewmodel.CalendarViewModel
import org.mascota.util.CalendarUtil.initCalendar
import java.util.*

class CalendarFragment : BindingFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {
    private val calendarViewModel: CalendarViewModel by viewModel()

    override fun initView() {
        initSwitchEvent()
        initClickEvent()
        initPart()
        initData()
        initCalendar()
        observeCurCalendar()
        observeDateItem()
    }

    private fun initPart() {
        binding.tvSwitchPart1.isSelected = true
    }

    private fun initSwitchEvent() {
        binding.apply {
            tvSwitchPart1.setOnClickListener {
                if (!it.isSelected) {
                    tvSwitchPart2.isSelected = false
                    it.isSelected = !it.isSelected
                }
            }
            tvSwitchPart2.setOnClickListener {
                if (!it.isSelected) {
                    tvSwitchPart1.isSelected = false
                    it.isSelected = !it.isSelected
                }
            }
        }
    }

    private fun initData() {
        calendarViewModel.getAuthorInfo()
        calendarViewModel.getDateItem(calendarViewModel.nowCalendar)
    }

    private fun observeCurCalendar() {
        calendarViewModel.apply {
            curCalendar.observe(viewLifecycleOwner) {
                binding.calendar.setCurCalendar(it)
                getDateItem(it)
            }
        }
    }

    private fun observeDateItem() {
        calendarViewModel.dateItem.observe(viewLifecycleOwner) {
            binding.apply {
                calendar.setCalendarData(it)
                tvName.text = it.data.name
            }
        }
    }

    private fun initCalendar() {
        binding.apply {
            calendarViewModel.setCalendar()

            with(calendar) {
                calendarViewModel.apply {
                    setMonthNextButtonClickListener {
                        addMonth()
                    }
                    setMonthPrevButtonClickListener {
                        minusMonth()
                    }
                    setYearNextButtonClickListener {
                        addYear()
                    }
                    setYearPrevButtonClickListener {
                        minusYear()
                    }
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
        startActivity(Intent(requireActivity(), DiaryWriteActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Ddfd", "dfddfd")
        calendarViewModel.nowCalendar = initCalendar(Calendar.getInstance(Locale.KOREA))
        calendarViewModel.setCalendar()
    }

    companion object {
        const val ANIMAL_ANGRY = 4
        const val ANIMAL_BORING = 5
        const val ANIMAL_JOY = 1
        const val ANIMAL_LOVE = 0
        const val ANIMAL_SAD = 3
        const val ANIMAL_USUAL = 2
        const val EMPTY = 100
    }
}