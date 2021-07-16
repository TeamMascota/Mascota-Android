package org.mascota.ui.view.rainbow

import android.app.Dialog
import android.content.Intent
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mascota.R
import org.mascota.databinding.*
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.diary.read.DiaryReadActivity
import org.mascota.ui.view.rainbow.adapter.FarewellAdapter
import org.mascota.ui.view.rainbow.adapter.FarewellAdapter.Companion.NOT_SELECTED
import org.mascota.ui.view.rainbow.adapter.HelpAdapter
import org.mascota.ui.view.rainbow.farewell.FarewellActivity
import org.mascota.ui.viewmodel.RainbowViewModel
import org.mascota.util.DialogUtil.makeDialog
import org.mascota.util.DialogUtil.makeLoadingDialog
import org.mascota.util.DialogUtil.setDialog
import org.mascota.util.DialogUtil.setLoadingDialog
import org.mascota.util.StringUtil.setTextPartialBold
import org.mascota.util.extension.setTextPartialColor
import org.mascota.util.extension.urlIntent

class RainbowFragment : BindingFragment<FragmentRainbowBinding>(R.layout.fragment_rainbow) {
    private val rainbowViewModel: RainbowViewModel by sharedViewModel()

    private val helpAdapter = HelpAdapter()
    private val farewellAdapter = FarewellAdapter()
    private lateinit var farewellDialog: Dialog
    private lateinit var helpDialog: Dialog
    private lateinit var finishDialog: Dialog
    private lateinit var loadingDialog: Dialog
    private lateinit var farewellDialogBinding: LayoutFarewellDialogBinding
    private lateinit var loadingDialogBinding: LayoutLoadingBinding
    private lateinit var helpMessageDialogBinding: LayoutHelpMessageDialogBinding
    private lateinit var finishDialogBinding: LayoutMascotaDialogBinding

    override fun initView() {
        initBookView()
        initData()
        initDialogDataBinding()
        initDialog()
        initLoadingDialog()
        initAdapter()
        initClickEvent()
        observeRainbowInfo()
        observeFarewellSelect()
    }

    private fun initLoadingDialog() {
        loadingDialog = makeLoadingDialog(requireContext())

        loadingDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.layout_loading,
            null,
            false
        )

        setLoadingDialog(loadingDialog, loadingDialogBinding.root)

        loadingDialog.show()
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
                farewellAdapter.setItemViewType(NOT_SELECTED)
            }
            tvNext.setOnClickListener {
                farewellDialog.dismiss()
                farewellAdapter.setItemViewType(NOT_SELECTED)
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
        helpAdapter.apply {
            binding.rvHelp.adapter = this
            setHelpMessageClickListener { requireContext().urlIntent(it) }
            setColorConverter { text, length ->
                setTextPartialColor(
                    R.color.maco_gray,
                    0,
                    length,
                    text
                )
            }
        }

        farewellAdapter.apply {
            farewellDialogBinding.rvPet.adapter = this
            setHeroClickListener { name, position ->
                rainbowViewModel.name = name
                finishDialogBinding.tvContent.text =
                    setTextPartialBold(0, name.length, "${name}의 이야기를 마무리하시겠어요?")
                setItemViewType(position)
            }
        }
    }

    private fun initData() {
        rainbowViewModel.getRainbowHome()
        rainbowViewModel.getFarewellSelect()
    }

    private fun observeFarewellSelect() {
        rainbowViewModel.farewellAnimalList.observe(viewLifecycleOwner) {
            farewellAdapter.data = it
        }
    }

    private fun observeRainbowInfo() {
        rainbowViewModel.rainbowHomeInfo.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            it.data.rainbowMainPage.apply {
                with(binding) {
                    tvRainbow.text = title

                    for (i in 1..memories.size) {
                        if (memories[i - 1] != null) {
                            if (i == 1) {
                                bvRainbow.setLeftRainbow(memories[i - 1])
                                bvRainbow.setLeftPageClickListener {
                                    /*startActivity(
                                        Intent(
                                            requireContext(),
                                            DiaryReadActivity::class.java
                                        )
                                    )*/
                                }
                            } else {
                                bvRainbow.setRightRainbow(memories[i - 1])
                                bvRainbow.setRightPageClickListener {
                                    /*startActivity(
                                        Intent(
                                            requireContext(),
                                            DiaryReadActivity::class.java
                                        )
                                    )*/
                                }
                            }
                        }
                    }

                    Glide.with(civProfile).load(bookImg).into(civProfile)

                    helpAdapter.data = help
                }
            }
        }
    }

    private fun startFarewell() {
        startActivity(
            Intent(requireContext(), FarewellActivity::class.java).putExtra(
                RAINBOW_PET_NAME, rainbowViewModel.name
            )
        )
    }

    companion object {
        const val IS_RAINBOW = false
        const val RAINBOW_PET_NAME = "rainbow_pet_name"
    }
}