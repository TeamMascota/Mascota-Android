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
        binding.tvSwitchPart1.setOnClickListener {
            it.isSelected = !it.isSelected
        }
        initClickEvent()
        getAuthorInfo()
        observeAuthorInfo()
    }

    private fun getAuthorInfo() {
        calendarViewModel.getAuthorInfo()
    }

    private fun observeAuthorInfo() {
        calendarViewModel.authorInfo.observe(viewLifecycleOwner) {
            binding.authorInfoData = it
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
}