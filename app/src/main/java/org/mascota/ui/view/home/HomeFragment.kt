package org.mascota.ui.view.home

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.FragmentHomeBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.viewmodel.HomeViewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel : HomeViewModel by viewModel()

    override fun initView() {
        getHomeBookInfo()
        observeHomeBookInfo()
        getHomeDiaryInfo()
        observeHomeDiaryInfo()
        getHomePageInfo()
        observeHomePageInfo()
    }

    private fun getHomeBookInfo() {
        homeViewModel.getHomeBookInfo()
    }

    private fun observeHomeBookInfo() {
        homeViewModel.homeBookInfo.observe(viewLifecycleOwner) {
            binding.homeBookInfoData = it
        }
    }

    private fun getHomeDiaryInfo() {
        homeViewModel.getHomeDiaryInfo()
    }

    private fun observeHomeDiaryInfo() {
        homeViewModel.homeDiaryInfo.observe(viewLifecycleOwner) {
            binding.bvHome.setLeftDiary(it)
        }
    }

    private fun getHomePageInfo() {
        homeViewModel.getHomePageInfo()
    }

    private fun observeHomePageInfo() {
        homeViewModel.homePageInfo.observe(viewLifecycleOwner) {
            binding.bvHome.setLeftDiaryFlag(it)
        }
    }




}