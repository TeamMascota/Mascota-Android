package org.mascota.ui.view.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.mascota.R
import org.mascota.databinding.FragmentBottomSheetBinding


class BottomSheet : BottomSheetDialogFragment() {
    private var callbackButtonClickListener : (() -> Unit)? = null

    private var _binding: FragmentBottomSheetBinding? = null
    protected val binding: FragmentBottomSheetBinding
        get() = requireNotNull(_binding)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_sheet, container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnSelect.setOnClickListener {
            callbackButtonClickListener?.invoke()
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dialog?.dismiss()
        }


        return binding.root

    }

    fun setCallbackButtonClickListener(listener: () -> Unit ){
        this.callbackButtonClickListener = listener

    }






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // 매개변수만 반환하면 의존성이 높아짐
        //setter -> 매개변수 접근, 반환로 정의해줌
        //




    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}