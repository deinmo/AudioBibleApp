package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.savedsinglechapter

import android.annotation.SuppressLint
import android.os.Build
import android.speech.tts.TextToSpeech
import android.text.Html
import android.widget.ScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deinmo.audiobibleapp.MainActivity
import com.deinmo.audiobibleapp.core.Screen
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun SavedChapterScreen(
    navController: NavController,
    viewModel: SavedChapterViewModel = hiltViewModel()
){
    val state = viewModel.state.value
  //  var context = LocalContext.current
    Scaffold() {
   // Box(modifier = Modifier
     //   .fillMaxSize()) {
        var content = ExpandedText(description = state.readablechapter?.content ?: "")
        FloatingActionButton(onClick = {
            navController.navigate(Screen.AudioPlayerScreen.route + "/${content}")
        }) {
            Text(text = "Speak")
        }
    //}
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
    textstyle: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.bodyLarge,
    expandable: Boolean = true,
    collapesdlines: Int = 3,
    expandadedlines: Int = Int.MAX_VALUE
): String {
    val text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
    }else{
        Html.fromHtml(description)
    }
    (Text(text = text.toString(),
        style = textstyle,modifier = Modifier.verticalScroll(rememberScrollState())))

    return text.toString()
}