package com.deinmo.audiobibleapp.audio_player_screen

data class AudioPlayerState(
    val image: String = "",
    val trackname: String = "",
    val currentSongIndex: Int = 0,
    val isPlaying: Boolean = false,
    val totalDuration: Long = 0L,
    val audioSliderState: AudioSliderState = AudioSliderState(),
    val audioControlstate: AudioControlstate = AudioControlstate()
)
