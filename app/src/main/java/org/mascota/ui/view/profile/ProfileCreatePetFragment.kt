package org.mascota.ui.view.profile

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mascota.R
import org.mascota.databinding.FragmentProfileCreatePetBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.custom.profile.ProfileBottomSheetDialog
import org.mascota.ui.view.profile.adapteer.HeroAdapter
import org.mascota.ui.view.profile.data.model.ResHero
import org.mascota.ui.viewmodel.ProfileViewModel
import org.mascota.util.CalendarUtil.convertCalendarToBeFamilyDateString
import org.mascota.util.StringUtil.makeHeroNumberingAndName
import org.mascota.util.StringUtil.makeTextLength
import org.mascota.util.extension.getColor
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.*

class ProfileCreatePetFragment :
    BindingFragment<FragmentProfileCreatePetBinding>(R.layout.fragment_profile_create_pet) {
    private val heroList = mutableListOf(ResHero(" ", true), ResHero(" ", false))
    private val nowCalendar = Calendar.getInstance(Locale.KOREA)
    private lateinit var heroAdapter: HeroAdapter
    private val profileViewModel: ProfileViewModel by sharedViewModel()

    private val requestImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val intent = activityResult.data
                if (intent != null) {
                    val fileUri = requireNotNull(intent.data)

                    val options = BitmapFactory.Options()
                    val inputStream: InputStream =
                        requireActivity().contentResolver.openInputStream(fileUri)!!
                    val bitmap =
                        requireNotNull(BitmapFactory.decodeStream(inputStream, null, options))

                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)

                    //val fileBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), fileUri)

                    /*profileViewModel.postRegisterPet(
                        mutableListOf(
                            MultipartBody.Part.createFormData(
                                "img",
                                "test.jpg",
                                fileBody
                            )
                        )
                    )*/
                }
            } else {

            }
        }

    override fun initView() {
        initAdapter()
        initClickEvent()
        initTextChangeEvent()
        initFocusChangeEvent()
    }

    private fun initAdapter() {
        heroAdapter = HeroAdapter()
        binding.apply {
            with(heroAdapter) {
                rvPet.adapter = this

                data = heroList

                tvPetNum.text = heroAdapter.itemCount.toString()

                setItemClickListener { position, isSelected ->
                    tvName.text = makeHeroNumberingAndName(position + 1)
                    setItemViewType(position)

                    if (isSelected) {
                        ProfileBottomSheetDialog().apply {
                            isCancelable = false
                            setAlbumClickListener { getLocalImage() }
                        }.show(childFragmentManager, PROFILE)
                    }
                }

                setQuitBtnClickListener {
                    heroList.removeAt(it)
                    heroAdapter.data = heroList
                }
            }
        }
    }

    private fun initTextChangeEvent() {
        binding.apply {
            etName.addTextChangedListener {
                tvNameSize.text = makeTextLength(etName.length())
            }
        }
    }

    private fun initClickEvent() {
        binding.apply {
            tvCat.setOnClickListener {
                it.isSelected = !it.isSelected
                tvDog.isSelected = false
            }
            tvDog.setOnClickListener {
                it.isSelected = !it.isSelected
                tvCat.isSelected = false
            }
            clDate.setOnClickListener {
                makeCalendarDialog()
            }
            btnMale.setOnClickListener {
                it.isSelected = !it.isSelected
                btnFemale.isSelected = false
                btnSecret.isSelected = false
            }
            btnFemale.setOnClickListener {
                it.isSelected = !it.isSelected
                btnMale.isSelected = false
                btnSecret.isSelected = false
            }
            btnSecret.setOnClickListener {
                it.isSelected = !it.isSelected
                btnMale.isSelected = false
                btnFemale.isSelected = false
            }
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
        val toBeFamilyCalendar = nowCalendar

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                toBeFamilyCalendar.set(year, month, day)
                binding.apply {
                    clDate.isSelected = true
                    tvDate.setTextColor(getColor(R.color.maco_black))
                    tvDate.text = convertCalendarToBeFamilyDateString(toBeFamilyCalendar)
                }
            },
            nowCalendar.get(Calendar.YEAR), nowCalendar.get(Calendar.MONTH), nowCalendar.get(
                Calendar.DAY_OF_MONTH
            )
        )
        datePickerDialog.datePicker.maxDate = nowCalendar.timeInMillis
        datePickerDialog.show()
    }

    private fun getLocalImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        requestImage.launch(intent)
    }

    private fun isEtNameEmpty() = binding.etName.text.isEmpty()

    companion object {
        const val PROFILE = "profile"
    }
}