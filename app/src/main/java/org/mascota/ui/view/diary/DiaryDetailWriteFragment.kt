package org.mascota.ui.view.diary

import android.app.DatePickerDialog
import org.mascota.R
import org.mascota.databinding.FragmentDiaryDetailWriteBinding
import org.mascota.ui.base.BindingFragment
import java.util.*


class DiaryDetailWriteFragment : BindingFragment<FragmentDiaryDetailWriteBinding>(R.layout.fragment_diary_detail_write) {
    val items = arrayOf("아이템1", "아이템2")


    override fun initView() {
        setBottomSheet()

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

    private fun setBottomSheet(){


        with(binding){
           iv1.setOnClickListener {
               val bottomSheet = BottomSheet()
               activity?.supportFragmentManager?.let { it1 -> bottomSheet.show(it1,bottomSheet.tag)


               }


           }
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
}
