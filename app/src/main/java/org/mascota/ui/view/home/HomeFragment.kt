package org.mascota.ui.view.home

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.FragmentHomeBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.home.adapter.HomeContentAdapter
import org.mascota.ui.viewmodel.HomeViewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel : HomeViewModel by viewModel()
    private lateinit var homeContentAdapter: HomeContentAdapter

    override fun initView() {
        getHomeBookInfo()
        observeHomeBookInfo()
        getHomeDiaryInfo()
        observeHomeDiaryInfo()
        getHomePageInfo()
        observeHomePageInfo()
        getHomeContentInfo()
        initHomeContentAdapter()
        observeHomeContentInfo()
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

    private fun getHomeContentInfo() {
        homeViewModel.getHomeContentInfo()
    }

    private fun initHomeContentAdapter() {
        homeContentAdapter = HomeContentAdapter()
        binding.rvContent.adapter = homeContentAdapter

        binding.rvContent.isNestedScrollingEnabled = false
    }

    private fun observeHomeContentInfo() {
        homeViewModel.homeContent.observe(viewLifecycleOwner) {
            homeContentAdapter.contentList = it
            homeContentAdapter.notifyDataSetChanged()
        }
    }
    

}