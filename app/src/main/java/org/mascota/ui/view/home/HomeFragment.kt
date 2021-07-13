package org.mascota.ui.view.home

import android.content.Intent
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.data.local.MascotaSharedPreference
import org.mascota.databinding.FragmentHomeBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.content.detail.ContentDetailActivity
import org.mascota.ui.view.content.edit.ContentEditActivity
import org.mascota.ui.view.diary.DiaryDetailWriteFragment
import org.mascota.ui.view.diary.DiaryEmotionFragment
import org.mascota.ui.view.diary.DiaryWriteActivity
import org.mascota.ui.view.diary.DiaryWriteActivity.Companion.PART1
import org.mascota.ui.view.home.adapter.HomeContentAdapter
import org.mascota.ui.viewmodel.HomeViewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var homeContentAdapter: HomeContentAdapter

    override fun initView() {
        initBookView()
        //getHomeBookInfo()
        //observeHomeBookInfo()
        //getHomeDiaryInfo()
        //observeHomeDiaryInfo()
        //getHomeContentInfo()
        initHomeContentAdapter()
        //observeHomeContentInfo()
        setEditBtnClickListener()
        observeHomePart1()
        checkPartData()

//        when (MascotaSharedPreference.getPart()) {
//            DiaryWriteActivity.PART1 -> listOf(DiaryEmotionFragment(), DiaryDetailWriteFragment())
//            else ->
        //getPart1, getPart2
        //observe 함수 갈아끼우기
        //observe안에서 작업들을 다한다.....왼쪽
    }

    private fun checkPartData() {
        when(MascotaSharedPreference.getPart()){
            PART1 -> observeHomePart1()
            else -> observeHomePart2()
        }
    }
    private fun getHomeBookInfo() {
        homeViewModel.getHomeBookInfo()
    }

    private fun observeHomeBookInfo() {
        homeViewModel.homeBookInfo.observe(viewLifecycleOwner) {
            //binding.homeBookInfoData = it
        }
    }

    private fun getHomeDiaryInfo() {
        homeViewModel.getHomeDiaryInfo()
    }

    private fun observeHomeDiaryInfo() {
        homeViewModel.homeDiaryInfo.observe(viewLifecycleOwner) {
            //binding.bvHome.setLeftDiary(it)
        }
    }

    private fun getHomeContentInfo() {
        homeViewModel.getHomeContentInfo()
    }

    private fun initHomeContentAdapter() {
        homeContentAdapter = HomeContentAdapter()
        binding.rvContent.adapter = homeContentAdapter
        homeContentAdapter.setNavigateContentDetail {
            startActivity(Intent(requireContext(), ContentDetailActivity::class.java))
        }
        binding.rvContent.isNestedScrollingEnabled = false
    }

    private fun observeHomeContentInfo() {
        homeViewModel.homeContent.observe(viewLifecycleOwner) {
            //homeContentAdapter.contentList = it
        }
    }

    private fun initBookView() {
        binding.bvHome.setWhereBookView(IS_HOME)
    }

    private fun setEditBtnClickListener() {
        binding.tvEdit.setOnClickListener {
            startActivity(Intent(requireContext(), ContentEditActivity::class.java))
        }
    }

    private fun observeHomePart1() {
        homeViewModel.getResHomePart1()
        homeViewModel.homePart1.observe(viewLifecycleOwner) {
            homeContentAdapter.contentList = it.tableContents
            binding.apply {
                tvHomeTitle.text = it.title
                Glide.with(civCover.context).load(it.bookImage).into(civCover)
                bvHome.setLeftPart1Diary(it.diary)
            }
        }
    }

    private fun observeHomePart2() {

    }

    companion object {
        const val IS_HOME = true
    }
}