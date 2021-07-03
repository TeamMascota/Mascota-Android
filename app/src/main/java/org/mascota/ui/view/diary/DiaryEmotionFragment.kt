package org.mascota.ui.view.diary


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.mascota.R
import org.mascota.databinding.FragmentDiaryEmotionBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.diary.adpter.SelectProfileAdapter
import org.mascota.ui.view.diary.data.ProfileList


class DiaryEmotionFragment : BindingFragment<FragmentDiaryEmotionBinding>(R.layout.fragment_diary_emotion){
    override fun initView() {

        val selectProfileAdapter = SelectProfileAdapter()
        // 리싸이클러뷰에 어댑터 넣어주기
        binding.rcvProfile.adapter = selectProfileAdapter
        //val linearlayoutmanager = LinearLayoutManager(context)
       // binding.rcvProfile.layoutManager = linearlayoutmanager
        binding.rcvProfile.setHasFixedSize(true)

        //어댑터에 데이터 넣고 갱신하기
        selectProfileAdapter.profileList.addAll(
            listOf<ProfileList>(
                ProfileList(
                    tv_name = "고봉이",
                    img_profile = R.drawable.cat
                ),
                ProfileList(
                    tv_name = "치삼이",
                    img_profile = R.drawable.cat2
                ),

                )
        )

        selectProfileAdapter.notifyDataSetChanged()



    }



}