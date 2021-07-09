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

        selectProfileAdapter.profileList.clear()

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
        selectEmotionaAdapter.emotionList.clear()


        binding.rcvEmotion.visibility = View.GONE

        selectProfileAdapter.setItemClickListenr(object :
        SelectProfileAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int, item : String) {

                Log.d("아이템명",item)

                selectEmotionaAdapter.emotionList.add(EmotionList(pet_name = item,))
                selectEmotionaAdapter.notifyDataSetChanged()
                binding.rcvEmotion.visibility = View.VISIBLE

            }
        }
        )


        selectEmotionaAdapter.setDeleteButtonClickListener {name, _ ->
            // 다이얼로그 창 띄우기
            showDeleteDialog(name)

        }


    }


    private fun showDeleteDialog(name : String) {
        diaogDeleteBinding = DataBindingUtil.inflate(LayoutInflater.from(requireContext())
            , R.layout.diaog_delete, null,false)


        diaogDeleteBinding.tvPetName.text  = name

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
