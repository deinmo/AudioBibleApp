package com.deinmo.audiobibleapp.audio_player_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AudioScreen(
    viewModel: AudioPlayerViewModel = hiltViewModel()
){
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f),
            contentAlignment = Alignment.Center
            ) {
            
        }
   //     Spacer(modifier = Modifier.height(32.dp))
        TitleArea(songName = viewModel.uiState.value.trackname)
   //     Spacer(modifier = Modifier.height(48.dp))
        MusicSlider(
            slidercolor = MaterialTheme.colorScheme.primary,
            audioSliderState = viewModel.uiState.value.audioSliderState
        )
        Spacer(modifier = Modifier.height(10.dp))
        ControlButtons(state = viewModel.uiState.value.audioControlstate)
    }
}

@Composable
private fun ControlButtons(
    state: AudioControlstate
    ){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
   //     verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Loopbutton(true) {}
        previoustrackbutton(isEnabled = state.ispreviousbuttonenabled,state.onPreviousplaybuttonClicked)         
        playpausebutton(isplaying = state.isplaying,isEnabled = state.isplaybuttonenabled,state.onplaybuttonClicked)
        nextbutton(isEnabled = state.isnextbuttonenabled,state.onNextbuttonClicked)
        playlistbutton(true,state.onPlaylistbuttonClicked) 
    }
}