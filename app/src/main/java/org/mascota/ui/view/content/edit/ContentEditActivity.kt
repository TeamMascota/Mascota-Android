package org.mascota.ui.view.content.edit

import android.app.Dialog
import android.view.Gravity
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.data.local.MascotaSharedPreference
import org.mascota.data.remote.model.response.content.ResContentList
import org.mascota.data.remote.model.response.content.ResPart2ContentList
import org.mascota.databinding.ActivityContentEditBinding
import org.mascota.databinding.LayoutContentEditDialogBinding
import org.mascota.databinding.LayoutHelpMessageDialogBinding
import org.mascota.databinding.LayoutMascotaDialogBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.content.edit.adapter.ContentEditAdapter
import org.mascota.ui.view.diary.DiaryWriteActivity
import org.mascota.ui.viewmodel.ContentViewModel
import org.mascota.util.DialogUtil
import org.mascota.util.StringUtil
import org.mascota.util.StringUtil.setTextPartialBold

class ContentEditActivity :
    BindingActivity<ActivityContentEditBinding>(R.layout.activity_content_edit) {

    private lateinit var deleteDialog: Dialog
    private lateinit var deleteCompleteDialog: Dialog
    private lateinit var addDialog: Dialog
    private lateinit var editDialog: Dialog
    private lateinit var deleteDialogBinding: LayoutMascotaDialogBinding
    private lateinit var deleteCompleteDialogBinding: LayoutHelpMessageDialogBinding
    private lateinit var addDialogBinding: LayoutContentEditDialogBinding
    private lateinit var editDialogBinding: LayoutContentEditDialogBinding

    private val contentViewModel: ContentViewModel by viewModel()
    private lateinit var contentEditAdapter: ContentEditAdapter

    override fun initView() {
        initDialogDataBinding()
        initDialog()
        initContentEditAdapter()
        setDialog()
        initDialogClickEvent()
        setAddClickListener()
        setBackBtnClickListener()
        checkPartData()
    }


    private fun checkPartData() {
        when (MascotaSharedPreference.getPart()) {
            DiaryWriteActivity.PART1 ->  observeResPart1ContentList()
            else -> observeResPart2ContentList()
        }
    }

    private fun getResContentList() {
        contentViewModel.getResContentList()
    }

    private fun getResPart2ContentList() {
        contentViewModel.getResPart2ContentList()
    }

    override fun onResume() {
        super.onResume()
        getResContentList()
    }

    private fun observeResPart1ContentList() {
        getResContentList()
        contentViewModel.resContentList.observe(this) {
            contentEditAdapter.contentEditList = it.data.tableContents.subList(1, it.data.tableContents.size - 1)
            binding.tvPrologTitle.text = it.data.tableContents[0].chapterTitle
        }
    }

    private fun observeResPart2ContentList() {
        getResPart2ContentList()
        contentViewModel.resPart2ContentList.observe(this) { resPart2ContentList ->
            contentEditAdapter.contentEditList = resPart2ContentList.data.tableContents.subList(1, resPart2ContentList.data.tableContents.size - 1).map{changeRes(it)}
            binding.tvPrologTitle.text = resPart2ContentList.data.tableContents[0].chapterTitle
        }
    }

    private fun changeRes(tableContent: ResPart2ContentList.Data.TableContent) : ResContentList.Data.TableContent{
        return ResContentList.Data.TableContent(tableContent.chapterId, tableContent.chapter, tableContent.chapterTitle)
    }

    private fun initContentEditAdapter() {
        contentEditAdapter = ContentEditAdapter()
        contentEditAdapter.apply {
            binding.rvContent.adapter = this
            setDeleteClickListener { chapter, chapterId ->
                val prefixWord = getString(R.string.delete_dialog_content)
                deleteDialogBinding.tvContent.apply {
                    text = setTextPartialBold(
                        prefixWord.length,
                        prefixWord.length + chapter.length,
                        prefixWord + chapter + getString(R.string.delete_dialog_question)
                    )
                    gravity = Gravity.CENTER
                }
                deleteCompleteDialogBinding.tvExplain.text = setTextPartialBold(
                    0, chapter.length, chapter + getString(R.string.delete_complete_msg)
                )
                contentViewModel.postChapterId(chapterId)
                deleteDialog.show()
            }
            setEditClickListener { chapter, title, chapterId ->
                editDialogBinding.apply {
                    tvTitle.text = chapter
                    etContent.setText(title)
                    contentViewModel.postChapterId(chapterId)
                }
                editDialog.show()
            }
        }
    }

    private fun initDialog() {
        DialogUtil.apply {
            deleteDialog = makeDialog(this@ContentEditActivity)
            deleteCompleteDialog = makeDialog(this@ContentEditActivity)
            addDialog = makeDialog(this@ContentEditActivity)
            editDialog = makeDialog(this@ContentEditActivity)
            setDialog(deleteDialog, deleteDialogBinding.root)
            setDialog(deleteCompleteDialog, deleteCompleteDialogBinding.root)
            setDialog(addDialog, addDialogBinding.root)
            setDialog(editDialog, editDialogBinding.root)
        }
    }

    private fun initDialogDataBinding() {
        deleteDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.layout_mascota_dialog,
            null,
            false
        )

        deleteCompleteDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.layout_help_message_dialog,
            null,
            false
        )

        addDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.layout_content_edit_dialog,
            null,
            false
        )

        editDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.layout_content_edit_dialog,
            null,
            false
        )
    }

    private fun setDialog() {
        deleteDialogBinding.apply {
            tvNext.text = getString(R.string.delete)
            tvNext.setTextColor(getColor(R.color.maco_orange))
            tvTitle.text = getString(R.string.content_delete)
        }
        deleteCompleteDialogBinding.apply {
            tvAboutHelp.text = getString(R.string.delete_complete)
            tvClose.setTextColor(getColor(R.color.maco_orange))
            tvClose.text = getString(R.string.check)
        }
        editDialogBinding.apply {
            tvAdd.text = getString(R.string.save)
        }
    }

    private fun initDialogClickEvent() {
        deleteDialogBinding.apply {
            tvQuit.setOnClickListener {
                deleteDialog.dismiss()
            }
            tvNext.setOnClickListener {
                contentViewModel.deleteContent()
                contentViewModel.getResContentList()
                deleteDialog.dismiss()
                deleteCompleteDialog.show()
            }
        }

        deleteCompleteDialogBinding.tvClose.setOnClickListener {
            deleteCompleteDialog.dismiss()
        }

        addDialogBinding.apply {
            etContent.addTextChangedListener {
                tvContentSize.text = StringUtil.makeTextLength(etContent.length())
            }
            tvQuit.setOnClickListener {
                addDialog.dismiss()
            }
            tvAdd.setOnClickListener {
                contentViewModel.postChapterTitle(etContent.text.toString())
                contentViewModel.postContentAdd()
                contentViewModel.getResContentList()
                addDialog.dismiss()
            }
        }

        editDialogBinding.apply {
            etContent.addTextChangedListener {
                tvContentSize.text = StringUtil.makeTextLength(etContent.length())
            }
            tvQuit.setOnClickListener {
                editDialog.dismiss()
            }
            tvAdd.setOnClickListener {
                contentViewModel.postChapterTitle(etContent.text.toString())
                contentViewModel.putContentEdit()
                contentViewModel.getResContentList()
                editDialog.dismiss()
            }
        }
    }

    private fun setAddClickListener() {
        binding.ivPlus.setOnClickListener {
            addDialog.show()
        }
    }

    private fun setBackBtnClickListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }


}