package org.mascota.ui.view.profile

import androidx.core.widget.addTextChangedListener
import org.mascota.R
import org.mascota.databinding.FragmentProfileCreateBookBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.util.StringUtil

class ProfileCreateBookFragment : BindingFragment<FragmentProfileCreateBookBinding>(R.layout.fragment_profile_create_book) {
    override fun initView() {
        initFocusChangeEvent()
        initTextChangeEvent()
    }

    private fun initFocusChangeEvent() {
        binding.apply {
            etBookTitle.setOnFocusChangeListener { _, hasFocus ->
                when (hasFocus) {
                    true -> clBookTitle.isSelected = true
                    else -> if (isEtBookTitleEmpty()) {
                        clBookTitle.isSelected = false
                    }
                }
            }
            etWriter.setOnFocusChangeListener { _, hasFocus ->
                when (hasFocus) {
                    true -> clWriter.isSelected = true
                    else -> if (isEtWriterNameEmpty()) {
                        clWriter.isSelected = false
                    }
                }
            }
            etPrologTitle.setOnFocusChangeListener { _, hasFocus ->
                when (hasFocus) {
                    true -> clPrologTitle.isSelected = true
                    else -> if (isEtPrologTitleEmpty()) {
                        clPrologTitle.isSelected = false
                    }
                }
            }
            etProlog.setOnFocusChangeListener { _, hasFocus ->
                when (hasFocus) {
                    true -> etProlog.isSelected = true
                    else -> if (isEtPrologEmpty()) {
                        etProlog.isSelected = false
                    }
                }
            }
        }
    }

    private fun initTextChangeEvent() {
        binding.apply {
            etWriter.addTextChangedListener {
                tvWriterSize.text = StringUtil.makeTextLength(etWriter.length())
            }
            etBookTitle.addTextChangedListener {
                tvBookTitleSize.text = StringUtil.makeTextLength(etBookTitle.length())
            }
            etPrologTitle.addTextChangedListener {
                tvPrologSize.text = StringUtil.makeTextLength(etPrologTitle.length())
            }
        }
    }

    private fun isEtWriterNameEmpty() = binding.etWriter.text.isEmpty()

    private fun isEtBookTitleEmpty() = binding.etBookTitle.text.isEmpty()

    private fun isEtPrologTitleEmpty() = binding.etPrologTitle.text.isEmpty()

    private fun isEtPrologEmpty() = binding.etProlog.text.isEmpty()
}