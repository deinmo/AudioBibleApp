package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.singlechapter

import android.os.Build
import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deinmo.audiobibleapp.core.Screen
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.entities.DataEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.time.format.TextStyle

@Composable
fun SingleScreen(
    viewModel: SingleChapterViewModel = hiltViewModel(),
    navController: NavController
){
    val state = viewModel.state.value
    var content: String
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.LightGray)){
        ExpandedText(description = state.readablechapter?.content ?: "")
        val bibleid = state.readablechapter?.bibleId
        val id = state.readablechapter?.id ?: "noid"
        val bookid = state.readablechapter?.bookId
        val number = state.readablechapter?.number
        val content = state.readablechapter?.content
        val versecount = state.readablechapter?.verseCount
        val data = DataEntity(id,bibleid,number,bookid,content,versecount)
        FloatingActionButton(onClick = {
            var bool  = viewModel.savechapters(data)
            if (!bool)
                navController.navigate(Screen.LoginScreen.route)
        }) {
            Text(text = "SAVE")
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
    ){
    val text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
    }else{
        Html.fromHtml(description)
    }
    
    Text(text = text.toString(),
    style = textstyle)
}