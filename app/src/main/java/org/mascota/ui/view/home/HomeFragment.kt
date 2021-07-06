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

        homeContentAdapter = HomeContentAdapter()
        binding.rvContent.adapter = homeContentAdapter

        homeViewModel.getHomeContentInfo()

        homeViewModel.homeContent.observe(viewLifecycleOwner) {
            homeContentAdapter.contentList.addAll(it)
            homeContentAdapter.notifyDataSetChanged()
        }
    }

    private fun getHomeBookInfo() {
        homeViewModel.getHomeBookInfo()
    }

    private fun observeHomeBookInfo() {
        homeViewModel.homeBookInfo.observe(viewLifecycleOwner) {
            binding.homeBookInfoData = it
        }
    }

}