package org.mascota.ui.view.diary

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.mascota.R
import org.mascota.databinding.FragmentDiaryDetailWriteBinding
import org.mascota.ui.base.BindingFragment
import java.util.*


@Suppress("DEPRECATION")
class DiaryDetailWriteFragment : BindingFragment<FragmentDiaryDetailWriteBinding>(R.layout.fragment_diary_detail_write) {
    val items = arrayOf("아이템1", "아이템2")
    val REQ_STORAGE_PERMISSION : Int = 1
    val REQ_GALLERY : Int = 1


    override fun initView() {
        initClickSelectImage()

        binding.btnPickerDate.setOnClickListener {
            showDatePicker()
        }

        /*


        binding.spinSelectChapter.adapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            items
        ) as SpinnerAdapter

        binding.spinSelectChapter.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2){
                    0->{

                    }
                    1->{

                    }
                    else->{

                    }

                }
            }




        }


    }*/

        /*override fun ArrayAdapter(
        bindingFragment: BindingFragment<FragmentDiaryDetailWriteBinding>,
        supportSimpleSpinnerDropdownItem: Int,
        items: Array<String>
    ): Adapter {
       //

    }*/


    }


    private fun initClickSelectImage(){


        with(binding){
            iv1.setOnClickListener {
                showBottomSheet()
            }

            iv2.setOnClickListener {
                showBottomSheet()
            }
            iv3.setOnClickListener {
                showBottomSheet()
            }

            iv4.setOnClickListener {
                showBottomSheet()
            }
            iv5.setOnClickListener {
                showBottomSheet()
            }
        }

    }

    private fun showBottomSheet(){
        val bottomSheet = BottomSheet()
        bottomSheet.setCallbackButtonClickListener {
            // 카메라 함수 실행하기
            selectGallery()
            Log.d("프래그멘트","누름")
        }
        activity?.supportFragmentManager?.let { it1 ->
            bottomSheet.show(it1, bottomSheet.tag)
        }


    }




    private fun showDatePicker(){
        val datePicker = Calendar.getInstance()
        val year = datePicker.get(Calendar.YEAR)
        val month = datePicker.get(Calendar.MONTH)
        val day = datePicker.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(

            requireContext(),
            R.style.MySpinnerDatePickerStyle,
            {_, year, monthOfYear, dayOfMonth ->
                //월이 0부터 시작하여 1을 더해줘야힘
                val month = monthOfYear + 1
                //선택한 날짜의 요일을 구하기 위한 캘린더
                val calendar = Calendar.getInstance()
                // 선택한 날짜 세팅
                calendar.set(year,monthOfYear,dayOfMonth)


                binding.btnPickerDate.text = "$year.$month.$dayOfMonth"


            },
            year,
            month,
            day
        )

        dpd.show()
    }


    private fun selectGallery(){
        var writePermission = context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.WRITE_EXTERNAL_STORAGE) }
        var readPermission = context?.let{ ContextCompat.checkSelfPermission(it,Manifest.permission.READ_EXTERNAL_STORAGE)}

        if(writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED)
        {
            //권한이 없을 경우 요청함
            context?.let{
                ActivityCompat.requestPermissions(
                    it as Activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQ_STORAGE_PERMISSION)
            }

        }
        else{
            //권한 있을 경우
            var intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            startActivityForResult(intent,REQ_GALLERY)
        }


        }

    }

