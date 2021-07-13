package org.mascota.ui.viewmodel

import androidx.lifecycle.ViewModel
import org.mascota.data.repository.user.UserRepository

class UserViewModel(private val userRepository : UserRepository) : ViewModel() {
}