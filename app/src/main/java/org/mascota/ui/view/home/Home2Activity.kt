package org.mascota.ui.view.home

import android.app.Dialog
import android.content.Intent
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.ActivityHome2Binding
import org.mascota.databinding.LayoutLoadingBinding
import org.mascota.ui.MainActivity
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.content.detail.ContentDetailActivity
import org.mascota.ui.view.content.edit.ContentEditActivity
import org.mascota.ui.view.diary.DiaryWriteActivity
import org.mascota.ui.view.home.adapter.HomeContentAdapter
import org.mascota.ui.viewmodel.HomeViewModel
import org.mascota.util.ColorFilterUtil
import org.mascota.util.DialogUtil
import org.mascota.util.StringUtil.makePartText

class Home2Activity : BindingActivity<ActivityHome2Binding>(R.layout.activity_home2){
    private lateinit var loadingDialog: Dialog
    private lateinit var loadingDialogBinding: LayoutLoadingBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var homeContentAdapter: HomeContentAdapter

    override fun initView() {
        initColorFilter()
        initLoadingDialog()
        observeHomePart1()
        initHomeContentAdapter()
        initBookView()
        setEditBtnClickListener()
        movePartTwo()
        navigateDiaryWriteActivity()
    }

    private fun initLoadingDialog() {
        loadingDialog = DialogUtil.makeLoadingDialog(this)

        loadingDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.layout_loading,
            null,
            false
        )

        DialogUtil.setLoadingDialog(loadingDialog, loadingDialogBinding.root)

        loadingDialog.show()
    }

    private fun navigateDiaryWriteActivity() {
        binding.bvHome.setWriteBtnClickListener{
            startActivity(Intent(this, DiaryWriteActivity::class.java))
        }
    }

    private fun initColorFilter() {
        ColorFilterUtil.setImgFilter(binding.ivBookImg)
    }

    private fun initHomeContentAdapter() {
        homeContentAdapter = HomeContentAdapter()
        binding.rvContent.adapter = homeContentAdapter
        homeContentAdapter.setNavigateContentDetail {
            val intent = Intent(this, ContentDetailActivity::class.java)
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
            startActivity(Intent(this, ContentEditActivity::class.java))
        }
    }

    private fun observeHomePart1() {
        homeViewModel.getResHomePart1()
        homeViewModel.homePart1.observe(this) {
            loadingDialog.dismiss()
            homeContentAdapter.contentList = it.data.firstPartMainPage.tableContents.subList(1, it.data.firstPartMainPage.tableContents.size - 1)
            binding.apply {
                with(it.data.firstPartMainPage) {
                    tvChapter.text = makePartText(PART)
                    tvPrologTitle.text = tableContents[0].chapterTitle
                    tvHomeTitle.text = title
                    Glide.with(civCover.context).load(bookImg).into(civCover)
                    bvHome.setLeftPart1Diary(diary)
                    tvWriterName.text = secondPartBook.author
                    tvFullDate.text = secondPartBook.date
                    imgurl = secondPartBook.imgs

                }
            }
        }
    }

    private fun movePartTwo(){
        binding.clBook.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }



    companion object {
        const val IS_HOME = true
        const val PART = 1
    }




}