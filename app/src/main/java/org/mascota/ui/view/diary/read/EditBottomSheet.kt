package org.mascota.ui.view.diary.read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.mascota.R
import org.mascota.databinding.FragmentBottomSheetBinding
import org.mascota.databinding.LayoutDiaryEditBottomSheetBinding

class EditBottomSheet : BottomSheetDialogFragment()  {
    // 이야기 수정 클릭 리스너
    private var callbackButtonClickListener : (() -> Unit)? = null
    private var callbackDeleteBtnClickListener : (() -> Unit)? = null



    private var _binding: LayoutDiaryEditBottomSheetBinding? = null
    protected val binding: LayoutDiaryEditBottomSheetBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.layout_diary_edit_bottom_sheet, container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.tvContentEdit.setOnClickListener {
            callbackButtonClickListener?.invoke()
            dismiss()
        }

        binding.tvQuit.setOnClickListener {
            dialog?.dismiss()
        }

        binding.tvDiaryDelete.setOnClickListener {
            callbackDeleteBtnClickListener?.invoke()
            dismiss()

        }


        return binding.root

    }


    fun setCallbackButtonClickListener(listener: () -> Unit ){
        this.callbackButtonClickListener = listener
    }

    fun setCallbackDeleteBtnClickListener(listener: () -> Unit){
        this.callbackDeleteBtnClickListener = listener
    }
}