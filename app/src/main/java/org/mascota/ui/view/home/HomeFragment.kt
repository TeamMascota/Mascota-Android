package org.mascota.ui.view.home

import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.data.local.MascotaSharedPreference
import org.mascota.data.remote.model.response.home.ResHomePart1
import org.mascota.data.remote.model.response.home.ResHomePart2
import org.mascota.databinding.FragmentHomeBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.content.detail.ContentDetailActivity
import org.mascota.ui.view.content.edit.ContentEditActivity
import org.mascota.ui.view.diary.DiaryWriteActivity
import org.mascota.ui.view.home.adapter.HomeContentAdapter
import org.mascota.ui.viewmodel.HomeViewModel
import org.mascota.util.ColorFilterUtil
import org.mascota.util.StringUtil.makePartText

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var homeContentAdapter: HomeContentAdapter

    override fun initView() {

        val part =  MascotaSharedPreference.getPart()

        initColorFilter()
        initBookView()
        initHomeContentAdapter()
        setEditBtnClickListener()
        checkPartData(part)
        observeHomePart2()
        navigateDiaryWriteActivity()
        movePart1()

    }

    private fun checkPartData(part : Int) {
        when (part) {
            1-> {
                observeHomePart1()
                hideBookView()
            }
            else -> {
                observeHomePart2()
                showBookView()
            }
        }
    }


    private fun initColorFilter() {
        ColorFilterUtil.setImgFilter(binding.ivBookImg)
    }

    private fun hideBookView(){
        binding.apply {
            clBook.visibility = View.GONE
            tvWriter.visibility = View.GONE
            tvWriterName.visibility = View.GONE
            tvPublishdate.visibility = View.GONE
            tvFullDate.visibility = View.GONE
        }
    }

    private fun showBookView(){
        binding.apply {
            initColorFilter()
            clBook.visibility = View.VISIBLE
            tvWriter.visibility = View.VISIBLE
            tvWriterName.visibility = View.VISIBLE
            tvPublishdate.visibility = View.VISIBLE
            tvFullDate.visibility = View.VISIBLE
            ivBookImg.visibility = View.VISIBLE

        }
    }

    private fun movePart1(){
        binding.clBook.setOnClickListener {
            startActivity(Intent(requireContext(), Home2Activity::class.java))
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
                    tvChapter.text = makePartText(PART_1)
                    tvPrologTitle.text = tableContents[0].chapterTitle
                    tvHomeTitle.text = title
                    Glide.with(civCover.context).load(bookImg).into(civCover)
                    bvHome.setLeftPart1Diary(diary)
                }
            }
        }
    }

    private fun observeHomePart2() {
        homeViewModel.getResHomePart2()
        homeViewModel.homePart2.observe(viewLifecycleOwner){
            homeContentAdapter.contentList = it.data.secondPartMainPage.tableContents.subList(1, it.data.secondPartMainPage.tableContents.size-1).map{
                changeResPart1(it)
            }

            binding.apply {
                with(it.data.secondPartMainPage){
                    tvChapter.text  = makePartText(PART_2)
                    tvHomeTitle.text = title
                    bvHome.setLeftPart2Diary(diary)
                    tvPrologTitle.text = tableContents[0].chapterTitle
                    Glide.with(civCover.context).load(bookImg).into(civCover)
                    tvWriterName.text = it.data.secondPartMainPage.firstPartBook.author
                    tvFullDate.text = it.data.secondPartMainPage.firstPartBook.date
                    imgurl = it.data.secondPartMainPage.bookImg

                }
            }

        }

    }

    private fun changeResPart1 (resHomePart2: ResHomePart2.Data.SecondPartMainPage.TableContent) : ResHomePart1.Data.FirstPartMainPage.TableContent {
        return ResHomePart1.Data.FirstPartMainPage.TableContent(resHomePart2.chapterId, resHomePart2.chapter, resHomePart2.chapterTitle, resHomePart2.episodePerChapterCount)

    }

    private fun navigateDiaryWriteActivity() {
        binding.bvHome.setWriteBtnClickListener{
            startActivity(Intent(requireContext(), DiaryWriteActivity::class.java))
        }
    }

    companion object {
        const val IS_HOME = true
        const val PART_1 = 1
        const val PART_2 = 2
    }
}