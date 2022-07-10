package com.deinmo.audiobibleapp.core

sealed class Screen(val route: String){
    object ChapterListScreen : Screen("chapters_list")
    object SingleChapterScreen : Screen("single_chapter")
    object BibleListScreen : Screen("Bible_list")
    object LoginScreen : Screen("login_screen")
    object SignUpScreen : Screen("signup_screen")
    object ProfileScreen : Screen("profile_screen")
    object SavedChapterScreen : Screen("saved_screen")
    object AudioPlayerScreen: Screen("audio_player")
    object HomeScreen: Screen("home_screen")
}
