package org.mascota.ui.view.user.signup

import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.mascota.R
import org.mascota.databinding.FragmentSignUpEndBinding
import org.mascota.ui.base.BindingFragment
import org.mascota.ui.viewmodel.UserViewModel


class SignUpEndFragment : BindingFragment<FragmentSignUpEndBinding> (R.layout.fragment_sign_up_end) {
    private val userViewModel : UserViewModel by sharedViewModel()

    override fun initView() {
        setId()
    }

    private fun setId() {
        binding.tvEmail.text = userViewModel.email
    }

}