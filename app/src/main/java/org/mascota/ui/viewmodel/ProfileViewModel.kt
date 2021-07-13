package org.mascota.ui.viewmodel

import androidx.lifecycle.ViewModel
import org.mascota.data.repository.profile.ProfileRepository

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
}