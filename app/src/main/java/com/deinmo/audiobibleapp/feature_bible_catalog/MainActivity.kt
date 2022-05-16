package com.deinmo.audiobibleapp.feature_bible_catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deinmo.audiobibleapp.core.Screen
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.BookData
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.BibleListItem
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.chapters.ChapterListScreen
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.singlechapter.SingleChapterScreen
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.ui.AudioBibleAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioBibleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.BibleListScreen.route
                    ){
                        composable(route = Screen.BibleListScreen.route){
                            BibleListScreen(navController = navController)
                        }
                        composable(route = Screen.ChaptersListScreen.route){
                            ChapterListScreen()
                        }
                        composable(route = Screen.BibleListScreen.route){
                            SingleChapterScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun BibleListScreen(
    navController: NavController,
    viewModel: BookslistViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.books){book: BookData ->
                BibleListItem(bookData = book, onitemclick = {
                    navController.navigate(Screen.ChaptersListScreen.route + "/${book.bibleid}" + "/${book.bookid}")
                })
            }
        }
        if(state.error.isNotBlank()){
            Text(text = state.error, textAlign = TextAlign.Center)
        }
        if(state.isloading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AudioBibleAppTheme {
        val navController = rememberNavController()
        BibleListScreen(navController = navController)
    }
}