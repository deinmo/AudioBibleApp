package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.singlechapter

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.deinmo.audiobibleapp.R
import com.deinmo.audiobibleapp.core.Screen
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.entities.DataEntity
import java.time.format.TextStyle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun SingleScreen(
    viewModel: SingleChapterViewModel = hiltViewModel(),
    navController: NavController
){
    val context = LocalContext.current
    val state = viewModel.state.value
    val bibleid = state.readablechapter?.bibleId
    val id = state.readablechapter?.id ?: "noid"
    val bookid = state.readablechapter?.bookId
    val number = state.readablechapter?.number
    val content = state.readablechapter?.content
    val versecount = state.readablechapter?.verseCount
    val reference = state.readablechapter?.reference
    val data = DataEntity(id, bibleid, number, bookid, content, versecount,reference)

    val text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(state.readablechapter?.content ?: "", Html.FROM_HTML_MODE_COMPACT)
    }else{
        Html.fromHtml(state.readablechapter?.content ?: "")
    }
    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(onClick = {
            var bool = viewModel.savechapters(data)
            if (!bool)
                navController.navigate(Screen.LoginScreen.route)
            else
                Toast.makeText(context, "successfully saved", Toast.LENGTH_LONG)
        }) {
            Text(text = "SAVE")
        }
    }, topBar = {
        TopAppBar(backgroundColor = MaterialTheme.colorScheme.inverseOnSurface,) {
             Text(text = "BiblePod",style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(250.dp))
            Icon(painter = painterResource(id = R.drawable.ic_baseline_volume_up_24), contentDescription = "",modifier = Modifier.clickable {
                viewModel.onPlayPauseButtonPressed(text.toString())
            })
    }
    }) {
            ExpandedText(description = state.readablechapter?.content ?: "")
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
    ){
    val text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
    }else{
        Html.fromHtml(description)
    }
    
    Text(text = text.toString(),
    style = textstyle, modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 100.dp, top = 60.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Preview(){
    val navController = rememberNavController()
    SingleScreen(navController = navController)
}