package org.mascota.ui.view.diary



import android.util.Log
import android.view.View
import android.widget.Toast
import org.mascota.R
import org.mascota.databinding.FragmentDiaryEmotionBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.view.diary.adpter.SelectEmotionAdapter
import org.mascota.ui.view.diary.adpter.SelectProfileAdapter
import org.mascota.ui.view.diary.data.EmotionList
import org.mascota.ui.view.diary.data.ProfileList


class DiaryEmotionFragment : BindingFragment<FragmentDiaryEmotionBinding>(R.layout.fragment_diary_emotion){
    override fun initView() {

        val selectEmotionaAdapter = SelectEmotionAdapter()
        val selectProfileAdapter = SelectProfileAdapter()
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


        /*일단 처음부터 다 띄워도고, 그럼 어댑터에서 데이터 전달해줄떄 이름도 전달해주면됨 띄우면됨
체크안되있으면 인비즈블
체크되있으면 비지블 하기
add식이면 순서 정렬해야해서 복잡함

*/

        binding.rcvEmotion.visibility = View.GONE

        selectProfileAdapter.setItemClickListenr(object :
        SelectProfileAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // rcv 안에 있는 아이템에 각각 접근해서 visible 바꿔주기
                // rcv 타입을 바꿔라? 필터기능 ?
                // 특정 조건에 맞게 끔 작동하게
                // 고봉이 클릭시 -> 고봉이 클릭값 true -> rcv 안에서 보여주는 아이템 바뀌기
                // notifychanged 호출
                // 바인드에 연곃할 떄 코봉이만 눌러져있따 알려주기 ?
                // 어댑터에 타입을 지정, 조건 바뀔떄마다 -> notifychanged 호출
                // 어댑터 setType , Type  검색하기
                binding.rcvEmotion.visibility = View.VISIBLE

            }
        }

        )





// 감정 선택뷰 나타나는거 -완료
        //프로필 선택시 색깔 다른거 - 완료


        //삭제버튼 누르면 인비쥬어블되는거
        // 감정선택 시 검정색 태두리있는거로 바뀌는거?
        // 감정 각각 선택클릭 이벤트
        //다음버튼 비활성화

// 클릭시

    }



}