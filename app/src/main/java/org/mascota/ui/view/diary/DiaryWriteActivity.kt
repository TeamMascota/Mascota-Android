package org.mascota.ui.view.diary

import org.mascota.R
import org.mascota.databinding.ActivityDiaryWriteBinding
import org.mascota.ui.base.BindingActivity

class DiaryWriteActivity : BindingActivity<ActivityDiaryWriteBinding>(R.layout.activity_diary_write){


    override fun initView() {
        binding.apply{

            //프래그맨트 생성
            val diaryemotionfragment = DiaryEmotionFragment()
            val diaryDetailWriteFragment = DiaryDetailWriteFragment()


            //프래그멘트 매니저가 프래그멘트를 관리할 작업 단위 생성(transaction)
            val transaction = supportFragmentManager.beginTransaction()
            // 어떤 뷰에, 무슨 프래그멘트를 넣을것인가
            transaction.add(R.id.frg_profile,diaryemotionfragment)
            transaction.commit()

            btnNext.setOnClickListener {
                val move_transacion = supportFragmentManager.
                    beginTransaction().
                    replace(R.id.frg_profile, diaryDetailWriteFragment)
                    .commit()
            }

        }

    }


}