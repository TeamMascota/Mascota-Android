package org.mascota.ui.view.diary



import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import org.mascota.R
import org.mascota.databinding.DiaogDeleteBinding
import org.mascota.databinding.FragmentDiaryEmotionBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.diary.adpter.SelectEmotionAdapter
import org.mascota.ui.view.diary.adpter.SelectProfileAdapter
import org.mascota.ui.view.diary.data.EmotionList
import org.mascota.ui.view.diary.data.ProfileList



class DiaryEmotionFragment : BindingFragment<FragmentDiaryEmotionBinding>(R.layout.fragment_diary_emotion){


    private lateinit var diaogDeleteBinding : DiaogDeleteBinding



    val selectEmotionaAdapter = SelectEmotionAdapter()
    val selectProfileAdapter = SelectProfileAdapter()


    override fun initView() {


        // 리싸이클러뷰에 어댑터 넣어주기
        binding.rcvEmotion.adapter = selectEmotionaAdapter
        binding.rcvProfile.adapter = selectProfileAdapter

        binding.rcvProfile.setHasFixedSize(true)
        binding.rcvEmotion.setHasFixedSize(true)

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

        selectEmotionaAdapter.emotionList.addAll(

            listOf<EmotionList>(
                EmotionList(
                    pet_name = "고봉이",
                ),
                EmotionList(
                    pet_name = "치삼이",
                )
            )
        )


        selectEmotionaAdapter.notifyDataSetChanged()
        selectProfileAdapter.notifyDataSetChanged()



        binding.rcvEmotion.visibility = View.GONE

        selectProfileAdapter.setItemClickListenr(object :
        SelectProfileAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int, item : String) {
                // rcv 안에 있는 아이템에 각각 접근해서 visible 바꿔주기
                // rcv 타입을 바꿔라? 필터기능 ?
                // 특정 조건에 맞게 끔 작동하게
                // 고봉이 클릭시 -> 고봉이 클릭값 true -> rcv 안에서 보여주는 아이템 바뀌기
                // notifychanged 호출
                // 바인드에 연곃할 떄 코봉이만 눌러져있따 알려주기 ?
                // 어댑터에 타입을 지정, 조건 바뀔떄마다 -> notifychanged 호출
                // 어댑터 setType , Type  검색하기
                Log.d("아이템명",item)



                binding.rcvEmotion.visibility = View.VISIBLE



            }
        }
        )


        selectEmotionaAdapter.setDeleteButtonClickListener {
            // 다이얼로그 창 띄우기
            showDeleteDialog()

        }





        //button1.isClickable = true  // Enabled
        //button2.isClickable = false // Disable



// 클릭시
        // 다음으로 넘어가려면 하면 리플레이스프래그멘트 쓰기!!!
    }





    private fun showDeleteDialog() {
        diaogDeleteBinding = DataBindingUtil.inflate(LayoutInflater.from(requireContext())
            , R.layout.diaog_delete, null,false)

        val diaolog_delete = AlertDialog.Builder(context,0).create()

        diaolog_delete.apply {
            setView(diaogDeleteBinding.root)
            setCancelable(false)
        }.show()



        diaogDeleteBinding.btnCancel.setOnClickListener{ diaolog_delete.dismiss()}
        diaogDeleteBinding.btnDelete.setOnClickListener{
            binding.rcvEmotion.visibility = View.GONE
            diaolog_delete.dismiss()

        }

    }




}
