package com.deinmo.audiobibleapp.feature_profile.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.deinmo.audiobibleapp.feature_profile.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    profileRepository: ProfileRepository
): ViewModel() {
    var profileRepository = profileRepository

    fun isSuccessful(email: String, password: String): Boolean? {
        return profileRepository.login(password, email)
    }

}