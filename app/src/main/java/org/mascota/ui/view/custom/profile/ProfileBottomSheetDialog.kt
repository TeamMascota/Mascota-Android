package org.mascota.ui.view.custom.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.mascota.databinding.LayoutProfileBottomSheetBinding

class ProfileBottomSheetDialog : BottomSheetDialogFragment() {
    private var _binding: LayoutProfileBottomSheetBinding? = null
    private val binding: LayoutProfileBottomSheetBinding
        get() = requireNotNull(_binding)

    private var albumClickListener: (() -> Unit) ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutProfileBottomSheetBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        initClickEvent()

        return binding.root
    }

    private fun initClickEvent() {
        binding.apply {
            clAlbumSelect.setOnClickListener {
                albumClickListener?.invoke()
                dismiss()
            }
            clDelete.setOnClickListener {

            }
            clQuit.setOnClickListener { dismiss() }
        }
    }

    fun setAlbumClickListener(listener: ()-> Unit) {
        this.albumClickListener = listener
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}