package org.mascota.ui.view.user.login

import android.app.Dialog
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import org.mascota.R
import org.mascota.databinding.ActivityLoginBinding
import org.mascota.databinding.LayoutHelpMessageDialogBinding
import org.mascota.ui.base.BindingActivity
import org.mascota.util.DialogUtil

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var loginFailDialog: Dialog
    private lateinit var loginFailDialogBinding: LayoutHelpMessageDialogBinding
    private var isNotIdEmpty = false
    private var isNotPwEmpty = false

    override fun initView() {
        initFocusChangeEvent()
        initDialogDataBinding()
        initDialog()
        setDialog()
        setLoginBtnClickListener()
        initTextChangeEvent()
    }

    private fun initFocusChangeEvent() {
        binding.apply {
            etId.setOnFocusChangeListener { _, hasFocus ->
                when (hasFocus) {
                    true -> clId.isSelected = true
                    else -> if (!isNotIdEmpty) {
                        clId.isSelected = false
                    }
                }
            }
            etPw.setOnFocusChangeListener { _, hasFocus ->
                when (hasFocus) {
                    true -> clPw.isSelected = true
                    else -> if (!isNotPwEmpty) {
                        clPw.isSelected = false
                    }
                }
            }
        }
    }

    private fun setLoginBtnClickListener() {
        binding.btnLogin.setOnClickListener {
            //아이디, 비밀번호 보내고 bool값 받아오기
            loginFailDialog.show()
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
            }
            etPw.addTextChangedListener {
                isNotPwEmpty = !it.isNullOrEmpty()
                enableLoginButton()
            }
        }
    }
}