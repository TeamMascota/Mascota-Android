package org.mascota.ui.view.rainbow.farewell

import android.app.Dialog
import android.os.Build
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mascota.R
import org.mascota.data.remote.model.response.rainbow.ResBestMoment
import org.mascota.databinding.FragmentFarewellBestMomentBinding
import org.mascota.databinding.LayoutLoadingBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.rainbow.adapter.BestMomentAdapter
import org.mascota.ui.viewmodel.RainbowViewModel
import org.mascota.util.AnimationUtil.getFadeInAnim
import org.mascota.util.DialogUtil
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class FarewellBestMomentFragment :
    BindingFragment<FragmentFarewellBestMomentBinding>(R.layout.fragment_farewell_best_moment) {
    private lateinit var loadingDialog: Dialog
    private lateinit var loadingDialogBinding: LayoutLoadingBinding
    private val rainbowViewModel: RainbowViewModel by sharedViewModel()
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
        binding.isVisible = false
        initAdapter()
        setToday()
        initLoadingDialog()
        getResBestMoment()
        observeResRainBowBestMoment()
    }

    private fun initLoadingDialog() {
        loadingDialog = DialogUtil.makeLoadingDialog(requireContext())

        loadingDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.layout_loading,
            null,
            false
        )

        DialogUtil.setLoadingDialog(loadingDialog, loadingDialogBinding.root)

        loadingDialog.show()
    }

    private fun observeResRainBowBestMoment() {
        rainbowViewModel.resBestMoment.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            binding.isVisible = true
            binding.clBest.startAnimation(requireContext().getFadeInAnim())
            it.data.apply {
                pet.name.apply {
                    binding.tvPetName.text = this
                    binding.tvPetName2.text = this
                    binding.tvPetName3.text = this
                    binding.tvPetName4.text = this
                    binding.tvPetName5.text = this
                    binding.tvPetName6.text = this
                    binding.petName7.text = this
                }
                loveAdapter.emo = Pair(pet.kind, 0)
                joyAdapter.emo = Pair(pet.kind, 1)
                usualAdapter.emo = Pair(pet.kind, 2)
                angryAdapter.emo = Pair(pet.kind, 4)
                sadAdapter.emo = Pair(pet.kind, 3)
                boringAdapter.emo = Pair(pet.kind, 5)
                binding.apply {
                    kind = pet.kind
                    emoLove = 0
                    emoJoy = 1
                    emoUsual = 2
                    emoAngry = 4
                    emoSad = 3
                    emoBoring = 5
                }
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
            : List<Pair<ResBestMoment.Data.TheBestMoment.Diary, ResBestMoment.Data.TheBestMoment.Diary>> {
        val diaryPairList =
            arrayListOf<Pair<ResBestMoment.Data.TheBestMoment.Diary, ResBestMoment.Data.TheBestMoment.Diary>>()
        var first: ResBestMoment.Data.TheBestMoment.Diary
        var second: ResBestMoment.Data.TheBestMoment.Diary
        for (i in list.indices step 2) {
            first = list[i]
            second = list[i + 1]
            if (first == null) {
                first = ResBestMoment.Data.TheBestMoment.Diary("NULL", 0, 0, "NULL", "NULL", "NULL")
            }
            if (second == null) {
                second =
                    ResBestMoment.Data.TheBestMoment.Diary("NULL", 0, 0, "NULL", "NULL", "NULL")
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

