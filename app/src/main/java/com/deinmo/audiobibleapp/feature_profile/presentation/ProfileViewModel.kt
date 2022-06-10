package com.deinmo.audiobibleapp.feature_profile.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deinmo.audiobibleapp.core.Resource
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases.GetAllSavedChaptersUseCase
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.biblebooks.BibleListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAllSavedChaptersUseCase: GetAllSavedChaptersUseCase
): ViewModel() {
    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    init {
        getbooks()
    }

    fun getbooks(){
        getAllSavedChaptersUseCase().onEach { resource ->
            when(resource){
                is Resource.Loading ->{
                    _state.value = ProfileState(isloading = true)
                }
                is Resource.Success ->{
                    _state.value = ProfileState(books = resource.data ?: emptyList())
                }
                is Resource.Error ->{
                    _state.value = ProfileState(error = resource.message ?: "An unexpected error occured")
                }
            }
        }.launchIn(viewModelScope)
    }
}