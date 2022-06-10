package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.savedsinglechapter

import android.os.Build
import android.speech.tts.TextToSpeech
import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.deinmo.audiobibleapp.MainActivity
import java.util.*

@Composable
fun SavedChapterScreen(
    viewModel: SavedChapterViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    var context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.LightGray)){
        var content = ExpandedText(description = state.readablechapter?.content ?: "")
        FloatingActionButton(onClick = {
            var tts = TextToSpeech(context) {
            }
            tts.language = Locale.UK
            tts.speak(content,TextToSpeech.QUEUE_FLUSH,null,"")
        }) {
            Text(text = "Speak")
        }
    }
    if(state.error.isNotBlank()){
        Text(text = state.error, textAlign = TextAlign.Center)
    }
    if(state.isloading){
        CircularProgressIndicator()
    }
}
@Composable
fun ExpandedText(
    description: String,
    modifier: Modifier = Modifier,
    textstyle: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.body2,
    expandable: Boolean = true,
    collapesdlines: Int = 3,
    expandadedlines: Int = Int.MAX_VALUE
): String {
    val text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
    }else{
        Html.fromHtml(description)
    }

    Text(text = text.toString(),
        style = textstyle)

    return text.toString()
}