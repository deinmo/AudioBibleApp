package com.deinmo.audiobibleapp.core

sealed class Screen(val route: String){
    object ChaptersListScreen : Screen("chapters_list")
    object SingleChapterScreen : Screen("single_chapter")
    object BibleListScreen : Screen("Bible_list")
}
