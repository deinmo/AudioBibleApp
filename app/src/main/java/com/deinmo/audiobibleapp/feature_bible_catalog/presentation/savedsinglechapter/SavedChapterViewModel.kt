package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.savedsinglechapter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deinmo.audiobibleapp.core.Resource
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.repository.BibleDataRepository
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases.AudioPlayerUseCase
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases.GetSavedChapterUseCase
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.singlechapter.SingleChapterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedChapterViewModel @Inject constructor(
    private val repository: BibleDataRepository,
    private val getSavedChapterUseCase: GetSavedChapterUseCase,
    private val audioPlayerUseCase: AudioPlayerUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(SavedChapterState())
    val state: State<SavedChapterState> = _state

    init {
        var id: String? = savedStateHandle.get<String>("id")
        getchapter(id)
    }

    fun getchapter(id: String?){
        getSavedChapterUseCase(id).onEach { data ->
            when(data){
                is Resource.Loading -> {
                    _state.value = SavedChapterState(isloading = true)
                }
                is Resource.Success -> {
                    _state.value = SavedChapterState(readablechapter = data.data)
                }
                is Resource.Error -> {
                    _state.value = SavedChapterState(error = data.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onPlayPauseButtonPressed(content: String) = viewModelScope.launch {
        content?.let { audioPlayerUseCase.playaudio(it) }
    }
}