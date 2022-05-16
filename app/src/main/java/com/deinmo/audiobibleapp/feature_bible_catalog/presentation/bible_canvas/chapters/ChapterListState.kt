package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.chapters

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Chapters

data class ChapterListState(
    val isloading: Boolean = false,
    val books: List<Chapters> = emptyList(),
    val error: String = ""
)
