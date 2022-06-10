package com.deinmo.audiobibleapp.feature_profile.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deinmo.audiobibleapp.core.Screen
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.BookData
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.biblebooks.BibleListItem
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.biblebooks.BookslistViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.books){book: Data ->
                ProfileListItem(data = book, onitemclick = {
                   navController.navigate(Screen.SavedChapterScreen.route + "/${book.id}")
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

@Composable
fun ProfileListItem(
    data: Data,
    onitemclick: (Data) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onitemclick(data) }
            .padding(16.dp)
    ) {
        data.bookId?.let { Text(text = it + data.number,style = MaterialTheme.typography.body1) }
    }
}