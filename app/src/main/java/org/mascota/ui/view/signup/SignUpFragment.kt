package org.mascota.ui.view.signup

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import androidx.core.content.ContextCompat
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mascota.R
import org.mascota.databinding.FragmentSignUpBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.viewmodel.UserViewModel


class SignUpFragment: BindingFragment<FragmentSignUpBinding> (R.layout.fragment_sign_up) {
    private var isValidId = false
    private var isCountEnable = false
    private var isPwEqual = false
    private val userViewModel : UserViewModel by sharedViewModel()


    override fun initView() {
        postBtnEnable()
        checkId()
        checkRePwd()
        checkPwd()
    }

    private fun postBtnEnable() {
        userViewModel.postBtnEnable(isValidSignUp())
    }

    private fun checkId(){

        with(binding){
            etEmail.addTextChangedListener(object : TextWatcher{
                override fun afterTextChanged(p0: Editable?) {
                    vaildcheckId()
                    postBtnEnable()
                    postId(etEmail.text.toString())
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    tvMsg.setText("")

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    vaildcheckId()
                    postBtnEnable()
                    postId(etEmail.text.toString())
                }

            })


        }
    }

    private fun vaildcheckId(){
        with(binding){
            val email = etEmail.text.toString()

            if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                tvMsg.setTextColor(ContextCompat.getColor(requireContext(),R.color.maco_blue))
                tvMsg.setText("사용할 수 있는 아이디입니다")
                isValidId = true
            }
            else{
                tvMsg.setTextColor(ContextCompat.getColor(requireContext(),R.color.maco_error))
                tvMsg.setText("사용할 수 없는 아이디입니다")
                isValidId = false
            }
        }


    }


    private fun checkRePwd(){
        with(binding){
            etRePwd.addTextChangedListener(object :TextWatcher{
                override fun afterTextChanged(p0: Editable?) {
                    equalsPwd()
                    postBtnEnable()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    tvPwdMsg.setText("")

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    equalsPwd()
                    postBtnEnable()
                }
            })

        }
    }

    private fun checkPwd(){

        with(binding){
            etPwd.addTextChangedListener(object : TextWatcher{

                val pwd  = etPwd.text.toString()
                override fun afterTextChanged(p0: Editable?) {
                    countPwd()
                    postBtnEnable()
                    postPass(etPwd.text.toString())
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    tvPwdMsg.setText("")
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    countPwd()
                    postBtnEnable()
                    postPass(etPwd.text.toString())
                }

            })

        }
    }

    private fun countPwd(){
        with(binding){
            val pwd  = etPwd.text.toString()
            if(pwd.length < 8){
                btnPwdCheck.visibility = View.GONE
                tvPwdMsg.setTextColor(ContextCompat.getColor(requireContext(),R.color.maco_error))
                tvPwdMsg.setText("비밀번호는 8자 이상이어야 합니다")
                isCountEnable = false
            }
            else{
                tvPwdMsg.setText("")
                btnPwdCheck.visibility = View.VISIBLE
                isCountEnable = true
            }
        }

    }

    private fun equalsPwd(){
        with(binding){
            val pwd  = etPwd.text.toString()
            val rePwd = etRePwd.text.toString()


            if(!rePwd.equals(pwd)){
                btnRePwdCheck.visibility = View.GONE
                tvRePwdMsg.setTextColor(ContextCompat.getColor(requireContext(),R.color.maco_error))
                tvRePwdMsg.setText("비밀번호가 일치하지 않습니다")
                isPwEqual = false
            }

            else{
                btnRePwdCheck.visibility = View.VISIBLE
                tvRePwdMsg.setText("")
                isPwEqual = true
            }
        }

    }

    private fun isValidSignUp() = isValidId && isPwEqual && isCountEnable

    private fun postId(id: String) {
        userViewModel.postId(id)
    }

    private fun postPass(pass: String) {
        userViewModel.postPass(pass)
    }

}