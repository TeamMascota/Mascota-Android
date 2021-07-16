package org.mascota.ui.view.diary


import android.app.Dialog
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mascota.R
import org.mascota.data.remote.model.request.diary.ReqPetDiaryWrite
import org.mascota.databinding.FragmentDiaryEmotionBinding
import org.mascota.databinding.LayoutMascotaDialogBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.diary.adpter.SelectEmotionAdapter
import org.mascota.ui.view.diary.adpter.SelectProfileAdapter
import org.mascota.ui.viewmodel.DiaryViewModel
import org.mascota.util.DialogUtil
import org.mascota.util.StringUtil
import org.mascota.util.extension.getColor


class DiaryEmotionFragment :
    BindingFragment<FragmentDiaryEmotionBinding>(R.layout.fragment_diary_emotion) {
    private lateinit var deleteDialog: Dialog
    private lateinit var deleteDialogBinding: LayoutMascotaDialogBinding
    private val isCompleteEmotion = MutableList(4) { true }
    private val diaryViewModel: DiaryViewModel by sharedViewModel()
    private val selectEmotionAdapter = SelectEmotionAdapter()
    private val selectProfileAdapter = SelectProfileAdapter()
    private var selectedProfileNum = 0

    override fun initView() {
        initDialogDataBinding()
        initDialog()
        initData()
        initAdapter()
        clickProfile()
        showDeleteEmotion()
        observePetInfo()
    }

    private fun initData() {
        diaryViewModel.getPetInfo()
    }

    private fun observePetInfo() {
        diaryViewModel.petInfo.observe(viewLifecycleOwner) {
            selectEmotionAdapter.data = it.data.pets
            Log.d("", selectEmotionAdapter.itemCount.toString())
            selectProfileAdapter.data = it.data.pets
        }
    }

    private fun showDeleteEmotion() {
        selectEmotionAdapter.setDeleteButtonClickListener { name, position ->
            deleteDialogBinding.tvContent.text = StringUtil.setTextPartialBold(
                0,
                name.length,
                "${name}(이)가 주인공에서 해제됩니다.\n기분을 삭제하시겠어요?"
            )
            showDeleteDialog(position)
        }
    }

    private fun clickProfile() {
        binding.apply {
            selectProfileAdapter.setItemClickListener { name, position ->
                deleteDialogBinding.tvContent.text = StringUtil.setTextPartialBold(
                    0,
                    name.length,
                    "${name}(이)가 주인공에서 해제됩니다.\n기분을 삭제하시겠어요?"
                )
                showDeleteDialog(position)
            }
        }
    }

    private fun initDialog() {
        deleteDialog = DialogUtil.makeDialog(requireContext())

        DialogUtil.setDialog(deleteDialog, deleteDialogBinding.root)
    }

    private fun initDialogDataBinding() {
        deleteDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.layout_mascota_dialog,
            null,
            false
        )

        deleteDialogBinding.apply {
            tvNext.text = getString(R.string.delete)
            tvNext.setTextColor(getColor(R.color.maco_orange))
            tvTitle.text = getString(R.string.emotion_delete)
            tvQuit.setOnClickListener {
                deleteDialog.dismiss()
            }
        }
    }

    private fun initAdapter() {
        binding.rcvEmotion.adapter = selectEmotionAdapter
        binding.rcvProfile.adapter = selectProfileAdapter

        selectEmotionAdapter.setEmoButtonClickListener { id, position, kind ->
            diaryViewModel.emotionList[position] = ReqPetDiaryWrite.Character(id, kind)
            isCompleteEmotion[position] = true
            diaryViewModel.postBtnEnable(isEnable())
        }

        selectProfileAdapter.setItemShowListener {
            selectEmotionAdapter.setSelectedList(it, true)
            ++selectedProfileNum
            isCompleteEmotion[it] = false
            diaryViewModel.postBtnEnable(isEnable())
            selectEmotionAdapter.notifyDataSetChanged()
        }
    }

    private fun isEnable(): Boolean {
        return isCompleteEmotion[0] && isCompleteEmotion[1] && isCompleteEmotion[2] && isCompleteEmotion[3] && selectedProfileNum > 0
    }

    override fun onResume() {
        super.onResume()
        diaryViewModel.postBtnEnable(isEnable())
    }

    private fun showDeleteDialog(position: Int) {
        deleteDialogBinding.tvNext.setOnClickListener {
            deleteDialog.dismiss()
            --selectedProfileNum
            isCompleteEmotion[position] = true
            diaryViewModel.postBtnEnable(isEnable())
            selectEmotionAdapter.setSelectedList(position, false)
            selectProfileAdapter.setSelectedList(position, false)
            diaryViewModel.emotionList[position] = diaryViewModel.emptyEmotionData
            selectEmotionAdapter.notifyDataSetChanged()
            selectProfileAdapter.notifyDataSetChanged()
        }

        deleteDialog.show()
    }
}
