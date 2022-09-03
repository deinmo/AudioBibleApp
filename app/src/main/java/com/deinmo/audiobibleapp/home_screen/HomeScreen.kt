package com.deinmo.audiobibleapp.home_screen

import android.app.DownloadManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.deinmo.audiobibleapp.R
import com.deinmo.audiobibleapp.core.Screen
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.BookData
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.biblebooks.BibleListItem
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.singlechapter.SingleScreen
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
        Row(modifier = Modifier.fillMaxWidth()) {
            var query by rememberSaveable {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                },
                modifier = Modifier
                    .padding(15.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                trailingIcon = { androidx.compose.material3.Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "",
                    modifier = Modifier.clickable { navController.navigate(Screen.SearchedScreenDemo.route + "/$query") }
                )}
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        val versions = listOf(VersionFeature("KJV",""),
            VersionFeature("NIV",""),VersionFeature("GNB",""),VersionFeature("NLT","")
        ,VersionFeature("NKJV",""),VersionFeature("RSB",""),VersionFeature("ASD",""))
        val topics = listOf("Love","Peace","joy","God","Jesus","Righteousness")
        LazyRow(modifier = Modifier.fillMaxWidth()){
            items(topics){
                TopicItem(data = it,
                    onitemclick = {
                        navController.navigate(Screen.SearchedScreenDemo.route + "/$it")
                    })
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp,end = 7.5.dp,bottom = 100.dp),
        modifier = Modifier.fillMaxSize()){
            items(versions.size){
                VersionItem(feature = versions[it])
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Preview(){
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}


@Composable
fun TopicItem(
    data: String,
    onitemclick: (String) -> Unit
) {
    OutlinedButton(onClick = {onitemclick},Modifier.padding(all = 4.dp)) {
        Text(text = data)
    }
}

@Composable
fun VersionItem(feature: VersionFeature){
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .background(MaterialTheme.colorScheme.tertiary)) {
            Text(
                text = feature.versioName,
                style = MaterialTheme.typography.headlineLarge,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(painter = painterResource(id = R.drawable.ic_baseline_play_circle_filled_24),
                contentDescription = "",
            tint = Color.White,
            modifier = Modifier.align(Alignment.BottomEnd))
        }
    }
}
@Composable
fun SearchBar(
    searchText: String,
    placeHolderText: String = "",
    onSearchTextChanged: (String) -> Unit,
    onClearClick: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
){

}