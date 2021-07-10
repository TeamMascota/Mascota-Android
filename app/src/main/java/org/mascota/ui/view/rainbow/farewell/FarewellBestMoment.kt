package org.mascota.ui.view.rainbow.farewell

import android.os.Build
import androidx.annotation.RequiresApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.FragmentFarewellBestMomentBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.rainbow.adapter.BestMomentAdapter
import org.mascota.ui.viewmodel.RainbowViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class FarewellBestMoment :
    BindingFragment<FragmentFarewellBestMomentBinding>(R.layout.fragment_farewell_best_moment) {

    private val rainbowViewModel: RainbowViewModel by viewModel()
    private lateinit var loveAdapter: BestMomentAdapter
    private lateinit var joyAdapter: BestMomentAdapter
    private lateinit var angryAdapter: BestMomentAdapter
    private lateinit var usualAdapter: BestMomentAdapter


    @RequiresApi(Build.VERSION_CODES.O)
    val currentDate: LocalDate = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    val forMatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    @RequiresApi(Build.VERSION_CODES.O)
    val tvToday = currentDate.format(forMatter).toString()

    override fun initView() {
        initData()
        initAdapter()
        observeData()
        setToday()

    }

    private fun setToday() {
        binding.tvToday.text = tvToday
    }


    private fun initData() {
        rainbowViewModel.getloveoMent()
        rainbowViewModel.getJoyMent()
        rainbowViewModel.getAngryMoment()
        rainbowViewModel.getUsualMoment()
    }

    private fun observeData() {
        rainbowViewModel.loveMoment.observe(viewLifecycleOwner) {
            loveAdapter.data = it.diaryList
        }
        rainbowViewModel.joyMoment.observe(viewLifecycleOwner) {
            joyAdapter.data = it.diaryList
        }
        rainbowViewModel.angryMoment.observe(viewLifecycleOwner) {
            angryAdapter.data = it.diaryList
        }

        rainbowViewModel.usualMoment.observe(viewLifecycleOwner) {
            usualAdapter.data = it.diaryList
        }
    }

    private fun initAdapter() {

        loveAdapter = BestMomentAdapter()
        joyAdapter = BestMomentAdapter()
        angryAdapter = BestMomentAdapter()
        usualAdapter = BestMomentAdapter()

        binding.rcvLove.adapter = loveAdapter
        binding.rcvJoy.adapter = joyAdapter
        binding.rcvAngry.adapter = angryAdapter
        binding.rcvUsual.adapter = usualAdapter

    }


}

