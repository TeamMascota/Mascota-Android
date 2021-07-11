package org.mascota.ui.view.content.edit

import android.app.Dialog
import android.view.Gravity
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import org.mascota.R
import org.mascota.databinding.ActivityContentEditBinding
import org.mascota.databinding.LayoutContentEditDialogBinding
import org.mascota.databinding.LayoutHelpMessageDialogBinding
import org.mascota.databinding.LayoutMascotaDialogBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.content.edit.adapter.ContentEditAdapter
import org.mascota.ui.view.content.edit.data.datasource.LocalContentEditDataSource
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

    override fun initView() {
        initDialogDataBinding()
        initDialog()
        initContentEditAdapter()
        setDialog()
        initDialogClickEvent()
        setAddClickListener()
    }

    private fun initContentEditAdapter() {
        ContentEditAdapter().apply {
            binding.rvContent.adapter = this
            val localContentEditDataSource = LocalContentEditDataSource()
            contentEditList = localContentEditDataSource.getContentEditInfoData()
            setDeleteClickListener {
                val prefixWord = getString(R.string.delete_dialog_content)
                deleteDialogBinding.tvContent.apply {
                    text = setTextPartialBold(
                        prefixWord.length,
                        prefixWord.length + it.length,
                        prefixWord + it + getString(R.string.delete_dialog_question)
                    )
                    gravity = Gravity.CENTER
                }
                deleteCompleteDialogBinding.tvExplain.text = setTextPartialBold(
                    0, it.length, it + getString(R.string.delete_complete_msg)
                )
                deleteDialog.show()
            }
            setEditClickListener { chapter, title ->
                editDialogBinding.apply {
                    tvTitle.text = chapter
                    etContent.setText(title)
                }
                editDialog.show()
            }
        }
    }

    private fun initDialog() {
        deleteDialog = DialogUtil.makeDialog(this)
        deleteCompleteDialog = DialogUtil.makeDialog(this)
        addDialog = DialogUtil.makeDialog(this)
        editDialog = DialogUtil.makeDialog(this)
        DialogUtil.setDialog(deleteDialog, deleteDialogBinding.root)
        DialogUtil.setDialog(deleteCompleteDialog, deleteCompleteDialogBinding.root)
        DialogUtil.setDialog(addDialog, addDialogBinding.root)
        DialogUtil.setDialog(editDialog, editDialogBinding.root)
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
                editDialog.dismiss()
            }
        }
    }

    private fun setAddClickListener() {
        binding.ivPlus.setOnClickListener {
            addDialog.show()
        }
    }
}