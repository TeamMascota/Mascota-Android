package org.mascota.ui.view.user.login

import android.app.Dialog
import android.content.Intent
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.mascota.R
import org.mascota.data.local.MascotaSharedPreference.getIsProfileCreate
import org.mascota.data.local.MascotaSharedPreference.getPetId
import org.mascota.data.local.MascotaSharedPreference.setLogin
import org.mascota.data.local.MascotaSharedPreference.setPetId
import org.mascota.data.local.MascotaSharedPreference.setUserId
import org.mascota.databinding.ActivityLoginBinding
import org.mascota.databinding.LayoutHelpMessageDialogBinding
import org.mascota.ui.MainActivity
import org.mascota.ui.base.BindingActivity
import org.mascota.ui.view.profile.ProfileCreateActivity
import org.mascota.ui.view.user.signup.SignUpActivity
import org.mascota.ui.viewmodel.UserViewModel
import org.mascota.util.DialogUtil
import org.mascota.util.EventObserver

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var loginFailDialog: Dialog
    private lateinit var loginFailDialogBinding: LayoutHelpMessageDialogBinding
    private var isNotIdEmpty = false
    private var isNotPwEmpty = false
    private val userViewModel : UserViewModel by viewModel()


    override fun initView() {
        initFocusChangeEvent()
        observeBtnEnable()
        initDialogDataBinding()
        initDialog()
        setDialog()
        observeSignIn()
        setLoginBtnClickListener()
        initTextChangeEvent()
    }

    private fun initFocusChangeEvent() {
        binding.apply {
            etId.setOnFocusChangeListener { _, hasFocus ->
                when (hasFocus) {
                    true -> {
                        clId.isSelected = true

                    }
                    else -> if (!isNotIdEmpty) {
                        clId.isSelected = false
                    }
                }
            }
            etPw.setOnFocusChangeListener { _, hasFocus ->
                when (hasFocus) {
                    true -> {
                        clPw.isSelected = true
                    }
                    else -> if (!isNotPwEmpty) {
                        clPw.isSelected = false
                    }
                }
            }
        }
    }

    private fun observeSignIn() {
        userViewModel.signInEvent.observe(this, EventObserver {
            when(it) {
                true -> {
                    finish()
                    setUserId(userViewModel.getUserID())
                    if(!getIsProfileCreate()) {
                        startActivity(Intent(this@LoginActivity, ProfileCreateActivity::class.java))
                    }
                    else {
                        setPetId(getPetId())
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        setLogin(true)
                    }
                }
                false -> loginFailDialog.show()
            }
            //binding.vpSignup.setCurrentItem(ProfileCreateActivity.SECOND_PAGE, true)
        })
    }

    private fun observeBtnEnable() {
        userViewModel.nextSignInEnaleEvent.observe(this, EventObserver{
            binding.btnLogin.isEnabled = it
        })
    }

    private fun setLoginBtnClickListener() {
        binding.btnLogin.setOnClickListener {
            //아이디, 비밀번호 보내고 bool값 받아오기
            userViewModel.postSignIn()
            //loginFailDialog.show()
        }

        binding.tvSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun initDialog() {
        DialogUtil.apply {
            loginFailDialog = makeDialog(this@LoginActivity)
            setDialog(loginFailDialog, loginFailDialogBinding.root)
        }
    }

    private fun initDialogDataBinding() {
        loginFailDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.layout_help_message_dialog,
            null,
            false
        )
    }

    private fun setDialog() {
        loginFailDialogBinding.apply {
            tvAboutHelp.text = getString(R.string.login_fail)
            tvExplain.text = getString(R.string.login_fail_msg)
            tvClose.apply {
                text = getString(R.string.check)
                setTextColor(getColor(R.color.maco_orange))
                setOnClickListener {
                    loginFailDialog.dismiss()
                }
            }
        }
    }

    private fun enableLoginButton() {
        binding.btnLogin.apply {
            isEnabled = isNotIdEmpty && isNotPwEmpty
        }
    }

    private fun initTextChangeEvent() {
        binding.apply {
            etId.addTextChangedListener {
                isNotIdEmpty = !it.isNullOrEmpty()
                enableLoginButton()
                userViewModel.postLoginId(binding.etId.text.toString())
            }
            etPw.addTextChangedListener {
                isNotPwEmpty = !it.isNullOrEmpty()
                enableLoginButton()
                userViewModel.postLoginPwd((binding.etPw.text.toString()))
            }
        }
    }


    private fun postLoginId(id: String) {
        userViewModel.postLoginId(id)

    }

    private fun postPwd(pass: String) {
        userViewModel.postLoginPwd(pass)
    }

}