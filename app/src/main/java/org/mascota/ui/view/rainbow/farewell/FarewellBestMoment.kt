package org.mascota.ui.view.rainbow.farewell

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.data.remote.model.response.rainbow.ResBestMoment
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
    private lateinit var sadAdapter: BestMomentAdapter
    private lateinit var boringAdapter: BestMomentAdapter


    @RequiresApi(Build.VERSION_CODES.O)
    val currentDate: LocalDate = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    val forMatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    @RequiresApi(Build.VERSION_CODES.O)
    val tvToday = currentDate.format(forMatter).toString()

    override fun initView() {
        initAdapter()
        setToday()
        getResBestMoment()
        observeResRainBowBestMoment()
    }

    private fun observeResRainBowBestMoment() {
        rainbowViewModel.resBestMoment.observe(viewLifecycleOwner){
            it.data.apply {
                pet.name.apply {
                    binding.tvPetName.text = this
                    binding.tvPetName2.text = this
                    binding.tvPetName3.text = this
                    binding.tvPetName4.text = this
                    binding.tvPetName5.text = this
                    binding.tvPetName6.text = this
                }
                loveAdapter.emo = Pair(pet.kind, 0)
                joyAdapter.emo = Pair(pet.kind, 1)
                usualAdapter.emo = Pair(pet.kind, 2)
                angryAdapter.emo = Pair(pet.kind, 4)
                sadAdapter.emo = Pair(pet.kind, 3)
                boringAdapter.emo = Pair(pet.kind, 5)
                loveAdapter.data = changePair(it.data.theBestMoments[0].diaries)
                joyAdapter.data = changePair(it.data.theBestMoments[1].diaries)
                usualAdapter.data = changePair(it.data.theBestMoments[2].diaries)
                angryAdapter.data = changePair(it.data.theBestMoments[4].diaries)
                sadAdapter.data = changePair(it.data.theBestMoments[3].diaries)
                boringAdapter.data = changePair(it.data.theBestMoments[5].diaries)
            }
        }
    }

    private fun changePair(list: List<ResBestMoment.Data.TheBestMoment.Diary>)
    : List<Pair<ResBestMoment.Data.TheBestMoment.Diary, ResBestMoment.Data.TheBestMoment.Diary>>{
        val diaryPairList = arrayListOf<Pair<ResBestMoment.Data.TheBestMoment.Diary, ResBestMoment.Data.TheBestMoment.Diary>>()
        var first : ResBestMoment.Data.TheBestMoment.Diary
        var second :ResBestMoment.Data.TheBestMoment.Diary
        for(i in list.indices step 2){
            first = list[i]
            second = list[i+1]
            if(first == null){
                first = ResBestMoment.Data.TheBestMoment.Diary(0,0,"","","")
            }
            if(second == null){
                second = ResBestMoment.Data.TheBestMoment.Diary(0,0,"","","")
            }
            diaryPairList.add(Pair(first, second))
        }
        return diaryPairList
    }

    private fun setToday() {
        binding.tvToday.text = tvToday
    }

    private fun getResBestMoment() {
        rainbowViewModel.getResRainBowBestMoment()
    }

    private fun initAdapter() {
        loveAdapter = BestMomentAdapter()
        joyAdapter = BestMomentAdapter()
        angryAdapter = BestMomentAdapter()
        usualAdapter = BestMomentAdapter()
        sadAdapter = BestMomentAdapter()
        boringAdapter = BestMomentAdapter()

        binding.apply {
            rcvLove.adapter = loveAdapter
            rcvJoy.adapter = joyAdapter
            rcvAngry.adapter = angryAdapter
            rcvUsual.adapter = usualAdapter
            rcvSad.adapter = sadAdapter
            rcvBoring.adapter = boringAdapter
        }
    }
}

