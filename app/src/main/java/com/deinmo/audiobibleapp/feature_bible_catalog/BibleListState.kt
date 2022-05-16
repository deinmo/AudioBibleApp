package com.deinmo.audiobibleapp.feature_bible_catalog

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.BookData

data class BibleListState(
    val isloading: Boolean = false,
    val books: List<BookData> = emptyList(),
    val error: String = ""
)
