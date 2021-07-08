package org.mascota.ui.view.rainbow

import android.app.Dialog
import android.content.Intent
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.FragmentRainbowBinding
import org.mascota.databinding.LayoutFarewellDialogBinding
import org.mascota.databinding.LayoutHelpMessageDialogBinding
import org.mascota.databinding.LayoutMascotaDialogBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.rainbow.adapter.FarewellAdapter
import org.mascota.ui.view.rainbow.adapter.HelpAdapter
import org.mascota.ui.view.rainbow.farewell.FarewellActivity
import org.mascota.ui.viewmodel.RainbowViewModel
import org.mascota.util.DialogUtil.makeDialog
import org.mascota.util.DialogUtil.setDialog
import org.mascota.util.extension.urlIntent

class RainbowFragment : BindingFragment<FragmentRainbowBinding>(R.layout.fragment_rainbow) {
    private val rainbowViewModel: RainbowViewModel by viewModel()

    private lateinit var helpAdapter: HelpAdapter
    private lateinit var farewellAdapter: FarewellAdapter
    private lateinit var farewellDialog: Dialog
    private lateinit var helpDialog: Dialog
    private lateinit var finishDialog: Dialog
    private lateinit var farewellDialogBinding: LayoutFarewellDialogBinding
    private lateinit var helpMessageDialogBinding: LayoutHelpMessageDialogBinding
    private lateinit var finishDialogBinding: LayoutMascotaDialogBinding

    override fun initView() {
        initBookView()
        initData()
        initDialogDataBinding()
        initDialog()
        initAdapter()
        initClickEvent()
        observeHelpInfo()
        observeRainbowInfo()
    }

    private fun initBookView() {
        binding.bvRainbow.setWhereBookView(IS_RAINBOW)
    }

    private fun initDialog() {
        requireContext().apply {
            farewellDialog = makeDialog(this)
            helpDialog = makeDialog(this)
            finishDialog = makeDialog(this)
        }

        setDialog(farewellDialog, farewellDialogBinding.root)
        setDialog(helpDialog, helpMessageDialogBinding.root)
        setDialog(finishDialog, finishDialogBinding.root)
    }

    private fun initDialogDataBinding() {
        farewellDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.layout_farewell_dialog,
            null,
            false
        )
        helpMessageDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.layout_help_message_dialog,
            null,
            false
        )
        finishDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.layout_mascota_dialog,
            null,
            false
        )
    }

    private fun initClickEvent() {
        binding.apply {
            ibQuestionMark.setOnClickListener { helpDialog.show() }
            btnFarewellPet.setOnClickListener {
                farewellDialog.show()
            }
        }

        helpMessageDialogBinding.tvClose.setOnClickListener { helpDialog.dismiss() }

        farewellDialogBinding.apply {
            tvQuit.setOnClickListener {
                farewellDialog.dismiss()
            }
            tvNext.setOnClickListener {
                farewellDialog.dismiss()
                finishDialog.show()
            }
        }

        finishDialogBinding.apply {
            tvQuit.setOnClickListener {
                finishDialog.dismiss()
            }
            tvNext.setOnClickListener {
                finishDialog.dismiss()
                startFarewell()
            }
        }
    }

    private fun initAdapter() {
        helpAdapter = HelpAdapter()
        farewellAdapter = FarewellAdapter()

        helpAdapter.apply {
            binding.rvHelp.adapter = this
            setHelpMessageClickListener { requireContext().urlIntent(it) }
        }

        farewellAdapter.apply {
            farewellDialogBinding.rvPet.adapter = this
            data = listOf("코봉", "녹차")
            setHeroClickListener { }
        }
    }

    private fun initData() {
        rainbowViewModel.getHelpInfo()
        rainbowViewModel.getRainbowInfo()
    }

    private fun observeHelpInfo() {
        rainbowViewModel.helpInfo.observe(viewLifecycleOwner) {
            helpAdapter.data = it
        }
    }

    private fun observeRainbowInfo() {
        rainbowViewModel.rainbowInfo.observe(viewLifecycleOwner) {
            it.apply {
                with(binding) {
                    for (i in 1..diaryList.size) {
                        if (i == 1) {
                            bvRainbow.setLeftRainbow(diaryList[i - 1])
                        } else
                            bvRainbow.setRightRainbow(diaryList[i - 1])
                    }
                }
            }
        }
    }

    private fun startFarewell() {
        startActivity(Intent(requireContext(), FarewellActivity::class.java))
    }

    companion object {
        const val IS_RAINBOW = false
    }
}