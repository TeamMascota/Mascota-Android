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
import org.mascota.ui.view.diary.DiaryWriteActivity.Companion.PART1
import org.mascota.ui.view.home.adapter.HomeContentAdapter
import org.mascota.ui.viewmodel.HomeViewModel
import org.mascota.util.StringUtil.makePartText

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var homeContentAdapter: HomeContentAdapter

    override fun initView() {
        initBookView()
        initHomeContentAdapter()
        setEditBtnClickListener()
        observeHomePart1()
        checkPartData()
    }

    private fun checkPartData() {
        when (MascotaSharedPreference.getPart()) {
            PART1 -> observeHomePart1()
            else -> observeHomePart2()
        }
    }

    private fun initHomeContentAdapter() {
        homeContentAdapter = HomeContentAdapter()
        binding.rvContent.adapter = homeContentAdapter
        homeContentAdapter.setNavigateContentDetail {
            val intent = Intent(requireContext(), ContentDetailActivity::class.java)
            intent.putExtra("chapterId", it)
            startActivity(intent)
        }
        binding.rvContent.isNestedScrollingEnabled = false
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
            homeContentAdapter.contentList = it.data.firstPartMainPage.tableContents.subList(1, it.data.firstPartMainPage.tableContents.size - 1)
            binding.apply {
                with(it.data.firstPartMainPage) {
                    tvChapter.text = makePartText(PART)
                    tvPrologTitle.text = tableContents[0].chapterName
                    tvHomeTitle.text = title
                    Glide.with(civCover.context).load(bookImg).into(civCover)
                    bvHome.setLeftPart1Diary(diary)
                }
            }
        }
    }

    private fun observeHomePart2() {

    }

    companion object {
        const val IS_HOME = true
        const val PART = 1
    }
}