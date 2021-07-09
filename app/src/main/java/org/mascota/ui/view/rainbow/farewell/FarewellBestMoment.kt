package org.mascota.ui.view.rainbow.farewell

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.FragmentFarewellBestMomentBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.rainbow.adapter.BestMomentAdapter
import org.mascota.ui.viewmodel.RainbowViewModel


class FarewellBestMoment : BindingFragment<FragmentFarewellBestMomentBinding>(R.layout.fragment_farewell_best_moment) {

    private val rainbowViewModel: RainbowViewModel by viewModel()
    private lateinit var bestAdapter : BestMomentAdapter

    override fun initView() {
        initData()
        initAdapter()
        observeLoveData()

    }



    private fun initData() {
        rainbowViewModel.getloveoMent()
    }

    private fun observeLoveData(){
        rainbowViewModel.loveMoment.observe(viewLifecycleOwner){
            bestAdapter.data = it.diaryList
        }
    }

    private fun initAdapter(){

        bestAdapter = BestMomentAdapter()
        bestAdapter.apply {
            binding.rcvLove.adapter = this

        }
    }







}

