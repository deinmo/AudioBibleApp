package com.deinmo.audiobibleapp.home_screen

import android.app.DownloadManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    var state by remember {
        mutableStateOf(viewModel.state.value)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        var query by rememberSaveable {
            mutableStateOf("")
        }
        TextField(
            value = query,
            onValueChange = { query = it },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            },
            modifier = Modifier
                .padding(15.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
        Button(onClick = {
           state.books = viewModel.getsearchedwordlist(query)
        }) {
            Text(text = "Search")
        }
        Box() {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.books) { book: Data ->
                    SearchedlistItem(data = book, onitemclick = {
                        navController.navigate(Screen.SavedChapterScreen.route + "/${book.id}")
                    })
                }
            }
            if (state.error.isNotBlank()) {
                Text(text = state.error, textAlign = TextAlign.Center)
            }
            if (state.isloading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
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
        data.id.let {
            if (it != null) {
                Text(text = it,style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}