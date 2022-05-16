package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.singlechapter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel

class SingleChapterScreen {

@Composable
fun SingleScreen(
    viewModel: singlechapterViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.LightGray)){
        Text(text = state.readablechapter?.content ?: "")
    }
    if(state.error.isNotBlank()){
        Text(text = state.error, textAlign = TextAlign.Center)
    }
    if(state.isloading){
        CircularProgressIndicator()
    }
}
}