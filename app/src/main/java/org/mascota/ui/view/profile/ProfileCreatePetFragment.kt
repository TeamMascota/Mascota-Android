package org.mascota.ui.view.profile

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mascota.R
import org.mascota.databinding.FragmentProfileCreatePetBinding
import org.mascota.databinding.LayoutHelpMessageDialogBinding
import org.mascota.databinding.LayoutMascotaDialogBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.custom.profile.ProfileBottomSheetDialog
import org.mascota.ui.view.diary.DiaryDetailWriteFragment.Companion.REQ_STORAGE_PERMISSION
import org.mascota.ui.view.profile.adapteer.HeroAdapter
import org.mascota.ui.view.profile.data.model.Pets
import org.mascota.ui.view.rainbow.farewell.FarewellExplainFragment.Companion.CAT
import org.mascota.ui.view.rainbow.farewell.FarewellExplainFragment.Companion.DOG
import org.mascota.ui.viewmodel.ProfileViewModel
import org.mascota.util.CalendarUtil.convertCalendarToBeFamilyDateString
import org.mascota.util.DialogUtil
import org.mascota.util.StringUtil.makeHeroNumbering
import org.mascota.util.StringUtil.makeHeroNumberingAndName
import org.mascota.util.StringUtil.makeTextLength
import org.mascota.util.dp
import org.mascota.util.extension.getColor
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.util.*

class ProfileCreatePetFragment :
    BindingFragment<FragmentProfileCreatePetBinding>(R.layout.fragment_profile_create_pet) {
    private var position = 0
    private var nowCalendar = Calendar.getInstance(Locale.KOREA)
    private lateinit var heroAdapter: HeroAdapter
    private var isPetNameExist = false
    private var isKindExist = false
    private var isDateExist = false
    private var isGenderExist = false
    private var gender = -1
    private var kind = -1
    private lateinit var alertDialog : Dialog
    private lateinit var deleteDialog : Dialog
    private lateinit var layoutHelpMessageDialogBinding: LayoutHelpMessageDialogBinding
    private lateinit var layoutMascotaDialogBinding: LayoutMascotaDialogBinding

    private val profileViewModel: ProfileViewModel by sharedViewModel()

    private val requestImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val intent = activityResult.data
                if (intent != null) {
                    val fileUri = requireNotNull(intent.data)
                    profileViewModel.imageUriList[position] = fileUri
                    heroAdapter.data = profileViewModel.imageUriList

                    val options = BitmapFactory.Options()
                    val inputStream: InputStream =
                        requireNotNull(requireActivity().contentResolver.openInputStream(fileUri))
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap!!.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
                    val fileBody = byteArrayOutputStream.toByteArray()
                        .toRequestBody("image/jpg".toMediaTypeOrNull())

                    val part = MultipartBody.Part.createFormData(
                        "images",
                        File(fileUri.toString()).name,
                        fileBody
                    )

                    profileViewModel.setImageList(position, part)
                }
            }
        }

    override fun initView() {
        initDialogBinding()
        initDialog()
        initClickEvent()
        initAdapter()
        initTextChangeEvent()
        initFocusChangeEvent()
    }

    private fun initDialogBinding() {
        layoutHelpMessageDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.layout_help_message_dialog,
            null,
            false
        )

        layoutMascotaDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.layout_mascota_dialog,
            null,
            false
        )

        layoutHelpMessageDialogBinding.apply {
            line.visibility = View.GONE
            tvAboutHelp.visibility = View.GONE
            tvClose.text = getString(R.string.check)
            tvClose.setTextColor(getColor(R.color.maco_orange))
            tvExplain.text = getString(R.string.profile_alert)
        }

        layoutMascotaDialogBinding.apply {
            tvQuit.setTextColor(getColor(R.color.maco_orange))
        }

    }

    private fun initDialog() {
        requireContext().apply {
            alertDialog = DialogUtil.makeDialog(this)
        }

        DialogUtil.setDialog(alertDialog, layoutHelpMessageDialogBinding.root)
    }

    private fun initAdapter() {
        heroAdapter = HeroAdapter()
        profileViewModel.addEmptyData()
        profileViewModel.imageUriList.add(null)
        profileViewModel.addImageList(null)
        binding.apply {
            with(heroAdapter) {
                rvPet.adapter = this

                data = profileViewModel.imageUriList

                tvHero.text = makeHeroNumbering(profileViewModel.imageUriList.size + 1)
                tvPetNum.text = heroAdapter.itemCount.toString()

                setItemClickListener { position, isSelected ->
                    if (isEnable()) {
                        tvName.text = makeHeroNumberingAndName(position + 1)
                        setItemViewType(position)
                        notifyDataSetChanged()
                        this@ProfileCreatePetFragment.position = position
                        setTempRestoreData()
                    } else {
                        //다이얼로그 띄우기
                        alertDialog.show()
                    }
                    if (isSelected) {
                        ProfileBottomSheetDialog().apply {
                            isCancelable = false
                            setAlbumClickListener { getLocalImage() }
                        }.show(childFragmentManager, PROFILE)
                    }
                }

                setQuitBtnClickListener {
                    //다이얼로그 띄우기, 클릭리스너
                    deleteDialog.show()
                    if (profileViewModel.imageUriList.size - 1 == it) {
                        setItemViewType(it - 1)
                        position = it - 1
                        tvName.text = makeHeroNumberingAndName(it)
                    } else {
                        setItemViewType(it)
                        position = it
                        tvName.text = makeHeroNumberingAndName(it + 1)
                    }
                    tvHero.text = makeHeroNumbering(profileViewModel.imageUriList.size)
                    profileViewModel.imageUriList.removeAt(it)
                    profileViewModel.petInfoDataList.removeAt(it)
                    setTempRestoreData()
                    clPlus.isVisible = profileViewModel.imageUriList.size < 4
                    heroAdapter.data = profileViewModel.imageUriList
                }
            }
        }
    }

    private fun initTextChangeEvent() {
        binding.apply {
            etName.addTextChangedListener {
                isPetNameExist = !etName.text.isNullOrEmpty()
                tvNameSize.text = makeTextLength(etName.length())
                updatePetInfoData()
                profileViewModel.postBtnEnable(isEnable())
            }
        }
    }

    private fun initClickEvent() {
        binding.apply {
            tvCat.setOnClickListener {
                it.isSelected = true
                tvDog.isSelected = false
                isKindExist = true
                kind = CAT
                updatePetInfoData()
                profileViewModel.postBtnEnable(isEnable())
            }
            tvDog.setOnClickListener {
                it.isSelected = true
                tvCat.isSelected = false
                isKindExist = true
                kind = DOG
                updatePetInfoData()
                profileViewModel.postBtnEnable(isEnable())
            }
            clDate.setOnClickListener {
                makeCalendarDialog()
            }
            btnMale.setOnClickListener {
                it.isSelected = true
                btnFemale.isSelected = false
                btnSecret.isSelected = false
                isGenderExist = true
                gender = MALE
                updatePetInfoData()
                profileViewModel.postBtnEnable(isEnable())
            }
            btnFemale.setOnClickListener {
                it.isSelected = true
                btnMale.isSelected = false
                btnSecret.isSelected = false
                isGenderExist = true
                gender = FEMALE
                updatePetInfoData()
                profileViewModel.postBtnEnable(isEnable())
            }
            btnSecret.setOnClickListener {
                Log.d("dfd", "dddd")
                it.isSelected = true
                btnMale.isSelected = false
                btnFemale.isSelected = false
                isGenderExist = true
                gender = SECRET
                updatePetInfoData()
                profileViewModel.postBtnEnable(isEnable())
            }
            clPlus.setOnClickListener {
                if (isEnable()) {
                    tvPetNum.text = profileViewModel.petInfoDataList.size.toString()
                    profileViewModel.imageUriList.add(null)
                    position = profileViewModel.imageUriList.size - 1
                    clPlus.isVisible = profileViewModel.imageUriList.size < 4
                    profileViewModel.postBtnEnable(false)
                    clearAllValue()
                    profileViewModel.addEmptyData()
                    profileViewModel.addImageList(null)
                    tvName.text = makeHeroNumberingAndName(profileViewModel.imageUriList.size)
                    tvHero.text = makeHeroNumbering(profileViewModel.imageUriList.size + 1)
                    heroAdapter.setItemViewType(profileViewModel.imageUriList.size - 1)
                    heroAdapter.data = profileViewModel.imageUriList
                } else {
                    //다이얼로그 띄우기
                    alertDialog.show()
                }
            }
        }
        layoutHelpMessageDialogBinding.tvClose.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    private fun initFocusChangeEvent() {
        binding.apply {
            etName.setOnFocusChangeListener { _, hasFocus ->
                when (hasFocus) {
                    true -> clName.isSelected = true
                    else -> if (isEtNameEmpty()) {
                        clName.isSelected = false
                    }
                }
            }
        }
    }

    private fun makeCalendarDialog() {
        nowCalendar = Calendar.getInstance()
        val toBeFamilyCalendar = nowCalendar

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                toBeFamilyCalendar.set(year, month, day)
                binding.apply {
                    clDate.isSelected = true
                    tvDate.isSelected = true
                    tvDate.text = convertCalendarToBeFamilyDateString(toBeFamilyCalendar)
                    isDateExist = true
                    ivCalendar.isSelected = true
                    updatePetInfoData()

                    profileViewModel.postBtnEnable(isEnable())
                }
            },
            nowCalendar.get(Calendar.YEAR), nowCalendar.get(Calendar.MONTH), nowCalendar.get(
                Calendar.DAY_OF_MONTH
            )
        )
        datePickerDialog.datePicker.maxDate = nowCalendar.timeInMillis
        datePickerDialog.show()
    }

    private fun isEtNameEmpty() = binding.etName.text.isEmpty()

    private fun isEnable() = isPetNameExist && isDateExist && isKindExist && isGenderExist

    private fun setNotExist() {
        isPetNameExist = false
        isKindExist = false
        isDateExist = false
        isGenderExist = false
    }

    private fun setAllExist() {
        isPetNameExist = true
        isKindExist = true
        isDateExist = true
        isGenderExist = true
    }

    private fun clearAllValue() {
        binding.apply {
            etName.text.clear()
            tvNameSize.text = makeTextLength(etName.length())
            tvDate.text = getString(R.string.yyyy_mm_dd)
            tvDate.isSelected = false
            tvCat.isSelected = false
            tvDog.isSelected = false
            btnMale.isSelected = false
            btnSecret.isSelected = false
            btnFemale.isSelected = false
            ivCalendar.isSelected = false
            setNotExist()
        }
    }

    private fun setTempRestoreData() {
        binding.apply {
            with(profileViewModel.petInfoDataList[position]) {
                clDate.isSelected = true
                tvDate.isSelected = true
                ivCalendar.isSelected = true
                tvDate.text = startDate
                etName.setText(name)
                tvNameSize.text = makeTextLength(etName.length())
                etName.isSelected = true
                setGender(this.gender)
                setKind(this.kind)
                updatePetInfoData()
                setAllExist()
                profileViewModel.postBtnEnable(isEnable())
            }
        }
    }

    private fun setGender(gender: Int) {
        this.gender = gender
        binding.apply {
            when (gender) {
                MALE -> {
                    btnMale.isSelected = true
                    btnSecret.isSelected = false
                    btnFemale.isSelected = false
                }
                FEMALE -> {
                    btnMale.isSelected = false
                    btnSecret.isSelected = false
                    btnFemale.isSelected = true
                }
                SECRET -> {
                    btnMale.isSelected = false
                    btnSecret.isSelected = true
                    btnFemale.isSelected = false
                }
            }
        }
    }

    private fun setKind(kind: Int) {
        this.kind = kind
        binding.apply {
            when (kind) {
                CAT -> {
                    tvCat.isSelected = true
                    tvDog.isSelected = false
                }
                DOG -> {
                    tvCat.isSelected = false
                    tvDog.isSelected = true
                }
            }
        }
    }

    private fun updatePetInfoData() {
        binding.apply {
            if (isEnable()) {
                tvPetNum.text = profileViewModel.petInfoDataList.size.toString()
                profileViewModel.petInfoDataList[position] =
                    Pets(
                        etName.text.toString(),
                        kind,
                        tvDate.text.toString(),
                        gender
                    )

            }
        }
    }

    private fun getLocalImage() {
        val writePermission = checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val readPermission = checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                REQ_STORAGE_PERMISSION
            )
        } else {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            requestImage.launch(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        profileViewModel.postBtnEnable(isEnable())
    }

    companion object {
        const val PROFILE = "profile"
        const val MALE = 0
        const val FEMALE = 1
        const val SECRET = 2
    }
}