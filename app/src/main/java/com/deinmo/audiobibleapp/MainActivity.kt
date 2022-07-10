package com.deinmo.audiobibleapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.AdapterView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.deinmo.audiobibleapp.audio_player_screen.AudioScreen
import com.deinmo.audiobibleapp.core.Screen
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.biblebooks.BibleListScreen
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.chapters.ChapterListScreen
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.singlechapter.SingleScreen
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.savedsinglechapter.SavedChapterScreen
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.ui.AudioBibleAppTheme
import com.deinmo.audiobibleapp.feature_profile.presentation.LoginScreen
import com.deinmo.audiobibleapp.feature_profile.presentation.ProfileScreen
import com.deinmo.audiobibleapp.feature_profile.presentation.SignUpScreen
import com.deinmo.audiobibleapp.home_screen.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity()
{
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioBibleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavBar(
                                items = listOf(
                                    BottomNavItem(
                                        "Books",
                                        Screen.HomeScreen.route,
                                        Icons.Default.Home
                                    ),
                                    BottomNavItem(
                                        "Login",
                                        Screen.BibleListScreen.route,
                                        Icons.Default.Email
                                    ),
                                    BottomNavItem(
                                        "Books",
                                        Screen.ProfileScreen.route,
                                        Icons.Default.Info
                                    )
                                ),
                                navController = navController,
                                onItemClick = {navController.navigate(it.route)
                                })
                        }
                    ) {
                        Navhostcontrol(navController = navController)
                    }
                }
            }
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun Navhostcontrol(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route
    ){
        composable(route = Screen.BibleListScreen.route){
            BibleListScreen(navController = navController)
        }
        composable(route = Screen.ChapterListScreen.route + "/{bibleid}" + "/{bookid}"){
            ChapterListScreen(navController = navController)
        }
        composable(route = Screen.SingleChapterScreen.route + "/{chapterid}" + "/{bibleid}"){
            SingleScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route){
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignUpScreen.route){
            SignUpScreen()
        }
        composable(route = Screen.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }
        composable(route = Screen.SavedChapterScreen.route + "/{id}"){
            SavedChapterScreen(navController = navController)
        }
        composable(route = Screen.AudioPlayerScreen.route + "/{content}"){
            AudioScreen()
        }
        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }
    }
}
@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick : (BottomNavItem) -> Unit
) {
    val backstack = navController.currentBackStackEntryAsState()
    /*BottomNavBar(items = items, navController = navController , onItemClick = {item ->
        onItemClick(item)})*/
    BottomAppBar(modifier = modifier,tonalElevation = 0.dp) {
        items.forEach { item ->
            val selected = item.route == backstack.value?.destination?.route
            NavigationBarItem(selected = selected, onClick = {
                onItemClick(item)
            },icon = { Icon(imageVector = item.icon, contentDescription = null)})
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