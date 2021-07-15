package org.mascota.ui.view.diary

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.mascota.R
import org.mascota.databinding.FragmentDiaryDetailWriteBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.diary.adpter.ImageAdapter
import org.mascota.ui.view.diary.adpter.SpinnerAdapter
import org.mascota.ui.view.diary.data.ImageList
import org.mascota.ui.view.diary.data.SpinnerModel
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.format.DateTimeFormatter


@Suppress("DEPRECATION")
class DiaryDetailWriteFragment : BindingFragment<FragmentDiaryDetailWriteBinding>(R.layout.fragment_diary_detail_write){
    val REQ_GALLERY = 12
    @RequiresApi(Build.VERSION_CODES.O)
    val current_date : LocalDate = now()
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    @RequiresApi(Build.VERSION_CODES.O)
    val tv_today = current_date.format(formatter).toString()

    private lateinit var spinnerAdapter: SpinnerAdapter
    private lateinit var imageAdapter: ImageAdapter
    private var item_spin = arrayListOf<SpinnerModel>()
    private val i = 0

    val image = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
// i 넘어갈떄마다
            activityResult ->
        if(activityResult.resultCode == Activity.RESULT_OK){
            val intent = activityResult.data
            if(intent != null){
                val fileUri = intent.data
                Log.d("사진","잘 가져옴")
               imageAdapter.imageList.add(ImageList(img = fileUri),)
               // imageAdapter.imageList.set(i,ImageList(img = fileUri))
                imageAdapter.notifyDataSetChanged()
            }
            else{
                Log.d("reusltcode","못함")
            }
        }

    }



    override fun initView() {
        initImageAdapter()
        setupSpinner()
        initClickSelectImage()
        binding.tvToday.setText(tv_today)
        WriteTitle()



    }

    private fun initImageAdapter(){
        imageAdapter = ImageAdapter()
        binding.rcvImage.adapter = imageAdapter
        binding.rcvImage.setHasFixedSize(true)
    }

    private fun initList(){
        item_spin.add(SpinnerModel(tv_chapter = "제 1장",tv_chatitle = "코봉이의 적응"))
        item_spin.add(SpinnerModel(tv_chapter = "제 2장", tv_chatitle = "코봉아 아프지마"))
        item_spin.add(SpinnerModel(tv_chapter = "제 3장", tv_chatitle = "코봉아 사랑해"))


    }

    private fun setupSpinner(){

       initList()

        spinnerAdapter = context?.let { SpinnerAdapter(it, R.layout.item_spinner, item_spin) }!!
        binding.spinSelectChapter.adapter = spinnerAdapter


    }


    private fun initClickSelectImage() {
        binding.btnAddImage.setOnClickListener{
            showBottomSheet()
        }

    }

    private fun showBottomSheet() {
        Log.d("바텀시트","열리기")
        val bottomSheet = BottomSheet()
        bottomSheet.setCallbackButtonClickListener {
            // 카메라 함수 실행하기
            selectGallery()
            Log.d("프래그멘트", "누름")
        }
        activity?.supportFragmentManager?.let { it1 ->
            bottomSheet.show(it1, bottomSheet.tag)
        }


    }


    private fun selectGallery() {
        var writePermission = context?.let {
            ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
        var readPermission = context?.let {
            ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }

        if (writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED) {
            //권한이 없을 경우 요청함
            context?.let {
                ActivityCompat.requestPermissions(
                    it as Activity,
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    REQ_STORAGE_PERMISSION
                )
            }

        } else {
            //권한 있을 경우
                Log.d("갤러리","잘옴")
           var intent = Intent(Intent.ACTION_PICK)
            //어떤 종류의 데이터를 선택할 수 있는지 정해줌
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            image.launch(intent)



        }
    }





    //갤러리에서 사진 선택한 후 호출
    //결과값이 넘어옴
    //인탠트로 이미지가 넘어옴




    private fun WriteTitle() {
        binding.etTitle.addTextChangedListener(object : TextWatcher{
            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(p0: Editable?) {
                var userinput = binding.etTitle.text.toString()
                binding.tvCount.text = userinput.length.toString() + " /11"

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tvCount.text = "0/11"

            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etTitle.text.toString()
                binding.tvCount.text = userinput.length.toString() + " /11"
            }


        })

    }

    companion object {
        const val REQ_STORAGE_PERMISSION = 1
    }

}