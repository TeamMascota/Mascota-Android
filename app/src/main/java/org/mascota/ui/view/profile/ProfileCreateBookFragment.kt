package org.mascota.ui.view.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mascota.R
import org.mascota.databinding.FragmentProfileCreateBookBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.custom.profile.ProfileBottomSheetDialog
import org.mascota.ui.view.diary.DiaryDetailWriteFragment
import org.mascota.ui.viewmodel.ProfileViewModel
import org.mascota.util.ColorFilterUtil.setImgFilter
import org.mascota.util.StringUtil
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

class ProfileCreateBookFragment :
    BindingFragment<FragmentProfileCreateBookBinding>(R.layout.fragment_profile_create_book) {
    private val profileViewModel: ProfileViewModel by sharedViewModel()
    private var isImageExist = false
    private var isBookTitleExist = false
    private var isWriterExist = false
    private var isPrologExist = false
    private var isPrologTitleExist = false

    override fun initView() {
        initClickEvent()
        initFocusChangeEvent()
        initTextChangeEvent()
    }

    private val requestImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val intent = activityResult.data
                if (intent != null) {
                    val fileUri = requireNotNull(intent.data)
                    Glide.with(binding.ivBookCover).load(fileUri).into(binding.ivBookCover)
                    isImageExist = true
                    setImgFilter(binding.ivBookCover)
                    binding.ivEmptyImg.isVisible = false
                    profileViewModel.postBtnEnable(isEnable())
                    profileViewModel.imgUri = fileUri
                    val options = BitmapFactory.Options()
                    val inputStream: InputStream =
                        requireNotNull(requireActivity().contentResolver.openInputStream(fileUri))
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap!!.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
                    val fileBody = byteArrayOutputStream.toByteArray()
                        .toRequestBody("image/jpeg".toMediaTypeOrNull())

                    val part = MultipartBody.Part.createFormData(
                        "image",
                        File(fileUri.toString()).name,
                        fileBody
                    )
                }
            }
        }

    private fun initClickEvent() {
        binding.clBook.setOnClickListener {
            ProfileBottomSheetDialog().apply {
                isCancelable = false
                setAlbumClickListener { getLocalImage() }
            }.show(childFragmentManager, ProfileCreatePetFragment.PROFILE)
        }
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
                isWriterExist = !etWriter.text.isNullOrEmpty()
                profileViewModel.writer = etWriter.text.toString()
                postBtnEnable()
                tvWriterSize.text = StringUtil.makeTextLength(etWriter.length())
            }
            etBookTitle.addTextChangedListener {
                isBookTitleExist = !etBookTitle.text.isNullOrEmpty()
                postBtnEnable()
                profileViewModel.title = etBookTitle.text.toString()
                tvBookTitleSize.text = StringUtil.makeTextLength(etBookTitle.length())
            }
            etPrologTitle.addTextChangedListener {
                isPrologTitleExist = !etPrologTitle.text.isNullOrEmpty()
                postBtnEnable()
                tvPrologSize.text = StringUtil.makeTextLength(etPrologTitle.length())
            }
            etPrologTitle.addTextChangedListener {
                isPrologTitleExist = !etPrologTitle.text.isNullOrEmpty()
                postBtnEnable()
                tvPrologSize.text = StringUtil.makeTextLength(etPrologTitle.length())
            }
            etProlog.addTextChangedListener {
                isPrologExist = !etProlog.text.isNullOrEmpty()
                postBtnEnable()
            }
        }
    }

    private fun postBtnEnable() {
        profileViewModel.postBtnEnable(isEnable())
    }

    private fun getLocalImage() {
        val writePermission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val readPermission = ContextCompat.checkSelfPermission(
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
                DiaryDetailWriteFragment.REQ_STORAGE_PERMISSION
            )
        } else {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            requestImage.launch(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.tvPetNum.text = profileViewModel.petInfoDataList.size.toString()
        profileViewModel.postBtnEnable(isEnable())
    }

    private fun isEnable() = isImageExist && isBookTitleExist && isPrologTitleExist && isPrologExist

    private fun isEtWriterNameEmpty() = binding.etWriter.text.isEmpty()

    private fun isEtBookTitleEmpty() = binding.etBookTitle.text.isEmpty()

    private fun isEtPrologTitleEmpty() = binding.etPrologTitle.text.isEmpty()

    private fun isEtPrologEmpty() = binding.etProlog.text.isEmpty()
}