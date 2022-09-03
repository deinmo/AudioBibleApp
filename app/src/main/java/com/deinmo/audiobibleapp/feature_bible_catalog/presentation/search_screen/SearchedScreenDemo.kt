package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.search_screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deinmo.audiobibleapp.core.Screen
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data

@Composable
fun SearchedScreen(
    navController: NavController,
viewModel: SearchedScreenViewModel = hiltViewModel()){
    val state = viewModel.state.value
    Box() {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.books) { book: Data ->
                SearchedlistItem(data = book, onitemclick = {
                    navController.navigate(Screen.SingleChapterScreen.route + "/${book.bookId}" + "/${book.bibleId}")
                })
            }
        }
        if (state.error.isNotBlank()) {
            Text(text = state.error, textAlign = TextAlign.Center)
        }
        if (state.isloading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
       //
    }
}

@Composable
fun SearchedlistItem(
    data: Data,
    onitemclick: (Data) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onitemclick(data) }
            .padding(16.dp)
    ) {
        Text(text = data.reference!!,style = MaterialTheme.typography.bodyLarge)
    }
}