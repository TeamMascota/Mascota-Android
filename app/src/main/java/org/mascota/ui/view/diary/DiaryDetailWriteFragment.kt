package org.mascota.ui.view.diary

import android.graphics.Insets.add
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import org.mascota.R
import org.mascota.databinding.FragmentDiaryDetailWriteBinding
import org.mascota.ui.base.BindingFragment


class DiaryDetailWriteFragment : BindingFragment<FragmentDiaryDetailWriteBinding>(R.layout.fragment_diary_detail_write) {
    val items = arrayOf("아이템1", "아이템2")


    override fun initView() {
        setBottomSheet()

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
               activity?.supportFragmentManager?.let { it1 -> bottomSheet.show(it1,bottomSheet.tag) }


           }
        }
    }
}
