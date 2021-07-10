package org.mascota.ui.view.signup

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import org.mascota.R
import org.mascota.databinding.ActivitySignUpBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.adapter.MascotaViewPagerAdapter
import org.mascota.ui.view.profile.ProfileCreateActivity
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.FIRST_PAGE
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.SECOND_PAGE

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private lateinit var mascotaViewPagerAdapter: MascotaViewPagerAdapter

    override fun initView() {
        initViewPager()
        initClick()
    }


    private fun initViewPager() {
        mascotaViewPagerAdapter = MascotaViewPagerAdapter(this@SignUpActivity)
        binding.apply {
            with(mascotaViewPagerAdapter) {
                fragmentList = listOf(SignUpFragment(), SignUpEndFragment())
                vpSignup.adapter = this
                vpSignup.isUserInputEnabled = false
            }
        }
    }


    private fun initClick(){

        binding.apply {
            with(vpSignup){
                btnSignup.setOnClickListener {
                    when(currentItem){
                        FIRST_PAGE ->setCurrentItem(SECOND_PAGE,true)
                        else -> startCreateProfileActivity()
                    }
                }

                btnBack.setOnClickListener {

                    when(currentItem){
                        FIRST_PAGE -> Log.d("로그인 액티비티","실행하기")
                        else -> setCurrentItem(FIRST_PAGE,true)
                    }

                }



                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                    override fun onPageSelected(position: Int) {
                        when(position){
                            FIRST_PAGE ->{
                                btnSignup.text = getString(R.string.signup)

                            }
                            else ->{
                                btnSignup.text = getString(R.string.register_pet)
                                btnBack.visibility = View.GONE

                            }
                        }
                    }




                })
            }
        }
    }
    private fun startCreateProfileActivity() {
        startActivity(Intent(this@SignUpActivity, ProfileCreateActivity::class.java))

    }

    private fun startLoginActivity(){
       // startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
    }
}