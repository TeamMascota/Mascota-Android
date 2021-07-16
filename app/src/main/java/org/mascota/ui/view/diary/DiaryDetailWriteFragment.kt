package org.mascota.ui.view.diary

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mascota.R
import org.mascota.data.local.MascotaSharedPreference.getPart
import org.mascota.databinding.FragmentDiaryDetailWriteBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.diary.DiaryWriteActivity.Companion.PART1
import org.mascota.ui.view.diary.adpter.ImageAdapter
import org.mascota.ui.view.diary.adpter.SpinnerAdapter
import org.mascota.ui.viewmodel.DiaryViewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.format.DateTimeFormatter


@Suppress("DEPRECATION")
class DiaryDetailWriteFragment :
    BindingFragment<FragmentDiaryDetailWriteBinding>(R.layout.fragment_diary_detail_write) {
    @RequiresApi(Build.VERSION_CODES.O)
    private val currentDate: LocalDate = now()
    private val diaryViewModel: DiaryViewModel by sharedViewModel()

    private var isSpinnerSelected = false
    private var isTitleExist = false
    private var isContentExist = false

    private var i = 0

    private val requestImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val intent = activityResult.data
                if (intent != null) {
                    val fileUri = requireNotNull(intent.data)
                    diaryViewModel.imageUriList[i] = fileUri
                    imageAdapter.data = diaryViewModel.imageUriList

                    diaryViewModel.addImageList(null)
                    imageAdapter.setSelectedList(i)

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

                    diaryViewModel.setImageList(i++, part)

                    if (i > 4)
                        binding.btnAddImage.isSelected = true
                }
            }
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

    @RequiresApi(Build.VERSION_CODES.O)
    private val tvToday = currentDate.format(formatter).toString()

    private lateinit var spinnerAdapter: SpinnerAdapter
    private lateinit var imageAdapter: ImageAdapter

    val image = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
// i 넘어갈떄마다
            activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK) {
            val intent = activityResult.data
            if (intent != null) {
                val fileUri = intent.data
                Log.d("사진", "잘 가져옴")

            } else {
                Log.d("reusltcode", "못함")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        getData()
        initImageAdapter()
        initObserver()
        initClickSelectImage()
        binding.tvToday.text = tvToday
        initTextChangeEvent()
    }

    private fun getContentPart1() {
        diaryViewModel.getContentPart1()
    }

    private fun getContentPart2() {
        diaryViewModel.getContentPart2()
    }

    private fun getData() {
        getContentPart1()
    }

    private fun initObserver() {
        diaryViewModel.contentList.observe(viewLifecycleOwner) {
            spinnerAdapter =
                SpinnerAdapter(requireContext(), R.layout.item_spinner, it.toMutableList())
            binding.spinSelectChapter.adapter = spinnerAdapter
            spinnerAdapter.setChapterClickListener {
                isSpinnerSelected = it
            }
        }
    }

    private fun initImageAdapter() {
        imageAdapter = ImageAdapter()
        imageAdapter.data = diaryViewModel.imageUriList
        binding.rcvImage.adapter = imageAdapter
    }

    private fun initClickSelectImage() {
        binding.btnAddImage.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        val bottomSheet = BottomSheet()
        bottomSheet.setCallbackButtonClickListener {
            // 카메라 함수 실행하기
            getLocalImage()
        }

        if(!binding.btnAddImage.isSelected)
            bottomSheet.show(requireActivity().supportFragmentManager, bottomSheet.tag)
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
                REQ_STORAGE_PERMISSION
            )

            if (writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                intent.type = "image/*"
                requestImage.launch(intent)
            }
        } else {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            requestImage.launch(intent)
        }
    }

    private fun initTextChangeEvent() {
        binding.apply {
            etStory.addTextChangedListener {
                diaryViewModel.contents = etStory.text.toString()
                isContentExist = !etStory.text.isNullOrEmpty()
                diaryViewModel.postBtnEnable(isEnable())
            }
            etTitle.addTextChangedListener {
                diaryViewModel.title = etTitle.text.toString()
                val userInput = binding.etTitle.text.toString()
                binding.tvCount.text = "(" + userInput.length.toString() + " /11)"
                isTitleExist = !etTitle.text.isNullOrEmpty()
                diaryViewModel.postBtnEnable(isEnable())
            }
        }
    }

    private fun isEnable(): Boolean {
        return isContentExist && isSpinnerSelected && isTitleExist
    }

    companion object {
        const val REQ_STORAGE_PERMISSION = 1
        const val REQ_GALLERY = 12
    }

    override fun onResume() {
        super.onResume()
        diaryViewModel.postBtnEnable(isEnable())
    }
}