package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.chapters

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Chapter

data class ChapterListState(
    val isloading: Boolean = false,
    val books: List<Chapter> = emptyList(),
    val error: String = ""
)
