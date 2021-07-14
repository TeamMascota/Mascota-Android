package org.mascota.ui.view.signup

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.databinding.ActivitySignUpBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.adapter.MascotaViewPagerAdapter
import org.mascota.ui.view.profile.ProfileCreateActivity
import org.mascota.ui.view.profile.ProfileCreateActivity.Companion.FIRST_PAGE
import org.mascota.ui.viewmodel.UserViewModel
import org.mascota.util.EventObserver

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private lateinit var mascotaViewPagerAdapter: MascotaViewPagerAdapter
    private val userViewModel : UserViewModel by viewModel()

    override fun initView() {
        observeSignUp()
        observeBtnEnable()
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

    private fun observeSignUp() {
        userViewModel.signUpEvent.observe(this, EventObserver {
            binding.vpSignup.setCurrentItem(ProfileCreateActivity.SECOND_PAGE, true)
        })
    }

    private fun observeBtnEnable() {
        userViewModel.nextBtnEnableEvent.observe(this, EventObserver{
            binding.btnSignup.isEnabled = it
        })
    }


    private fun initClick(){

        binding.apply {
            with(vpSignup){
                btnSignup.setOnClickListener {
                    when(currentItem){
                        FIRST_PAGE -> {
                            userViewModel.postSignUp()
                        }
                        else -> finish()
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
                                btnBack.visibility = View.INVISIBLE
                                tvAgree.visibility = View.INVISIBLE
                                tvSignup.visibility = View.INVISIBLE

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