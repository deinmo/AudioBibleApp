package com.deinmo.audiobibleapp

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.AdapterView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Login
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
import com.deinmo.audiobibleapp.core.Screen
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.biblebooks.BibleListScreen
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.chapters.ChapterListScreen
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.singlechapter.SingleScreen
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.savedsinglechapter.SavedChapterScreen
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.ui.AudioBibleAppTheme
import com.deinmo.audiobibleapp.feature_profile.presentation.LoginScreen
import com.deinmo.audiobibleapp.feature_profile.presentation.ProfileScreen
import com.deinmo.audiobibleapp.feature_profile.presentation.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity(),TextToSpeech.OnInitListener
{
    var tts: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(this,this)
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
                                        Screen.BibleListScreen.route,
                                        Icons.Default.Home
                                    ),
                                    BottomNavItem(
                                        "Login",
                                        Screen.LoginScreen.route,
                                        Icons.Default.Login
                                    ),
                                    BottomNavItem(
                                        "Books",
                                        Screen.ProfileScreen.route,
                                        Icons.Default.Image
                                    )
                                ),
                                navController = navController,
                                onItemClick = {navController.navigate(it.route)
                                })
                        }
                    ) {
                        Navhostcontrol(navController = navController, tts!!)
                    }
                }
            }
        }
    }

    override fun onInit(p0: Int) {
        if (p0 == TextToSpeech.SUCCESS){
            tts?.language = Locale.UK
        }
    }
}


@Composable
fun Navhostcontrol(navController: NavHostController,tts: TextToSpeech){
    NavHost(navController = navController, startDestination = Screen.BibleListScreen.route
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
            SavedChapterScreen(tts)
        }
    }
}
@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick : (BottomNavItem) -> Unit
){
    val backstack = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        elevation = 5.dp,
        backgroundColor = Color.Cyan
    ) {
        items.forEach { item ->
            val selected = item.route == backstack.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon =  {
                    Icon(imageVector = item.icon, contentDescription = item.name)
                }
            )
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