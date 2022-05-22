package com.deinmo.audiobibleapp.core

sealed class Screen(val route: String){
    object ChapterListScreen : Screen("chapters_list")
    object SingleChapterScreen : Screen("single_chapter")
    object BibleListScreen : Screen("Bible_list")
}
