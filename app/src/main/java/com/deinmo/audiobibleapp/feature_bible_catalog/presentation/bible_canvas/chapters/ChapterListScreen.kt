package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.chapters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deinmo.audiobibleapp.core.Screen
import dagger.hilt.android.AndroidEntryPoint


@Composable
fun ChapterListScreen(
        navController: NavController,
        viewModel: ChapterlistViewModel = hiltViewModel()
    ){
        val state = viewModel.state.value
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp)){
                items(state.books){chapter ->
                    ChapterListItem(chapter= chapter, onitemclick = {
                        navController.navigate(Screen.SingleChapterScreen.route + "/${chapter.id}" + "/${chapter.bibleid}")
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

