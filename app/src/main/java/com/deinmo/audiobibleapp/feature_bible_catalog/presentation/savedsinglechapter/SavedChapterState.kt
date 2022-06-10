package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.savedsinglechapter

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data

data class SavedChapterState(
    val isloading: Boolean = false,
    val readablechapter: Data? = null,
    val error: String = ""
)
