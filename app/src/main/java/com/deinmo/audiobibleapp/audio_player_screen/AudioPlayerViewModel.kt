package com.deinmo.audiobibleapp.audio_player_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases.AudioPlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioPlayerViewModel @Inject constructor(
    private val audioPlayerUseCase: AudioPlayerUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    private val _uistate = mutableStateOf(AudioPlayerState(audioControlstate = getmusicControlState()))
    val uiState: State<AudioPlayerState> = _uistate
    val content: String?
    init {
         content = savedStateHandle.get<String>("content")
        collectTimePassed()
        setMusicSliderState()
    }

    private fun setmusicControlState() = viewModelScope.launch {
        _uistate.value = uiState.value.copy(
            audioControlstate = uiState.value.audioControlstate.copy()
        )
    }

    private fun getmusicControlState() = AudioControlstate (
        onplaybuttonClicked = this::onPlayPauseButtonPressed
            )
    private fun setMusicSliderState() = viewModelScope.launch {
        _uistate.value = uiState.value.copy(
            audioSliderState = uiState.value.audioSliderState.copy(
                onValueChange = this@AudioPlayerViewModel::onSeekBarValueChanged
            )
        )
    }

    private fun onSeekBarValueChanged(value: Float) = viewModelScope.launch {
        _uistate.value = uiState.value.copy(
            audioSliderState = uiState.value.audioSliderState.copy(sliderValue = value)
        )
        val seektime = (value * uiState.value.totalDuration).toLong()
    }

    private fun onPlayPauseButtonPressed() = viewModelScope.launch {
        content?.let { audioPlayerUseCase.playaudio(it) }
    }

    private fun updateDurationUI(currentTime: Long, totalTime: Long){
        if (totalTime == 0L)
            return
            _uistate.value = uiState.value.copy(
                audioSliderState = uiState.value.audioSliderState.copy(
                    timepassed = currentTime,
                    timeleft = totalTime - currentTime,
                    sliderValue = currentTime/totalTime.toFloat()
                )
            )
    }

    private fun collectTimePassed() = viewModelScope.launch {

    }
}