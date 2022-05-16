package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.singlechapter

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.ChaptersData
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data

data class SingleChapterState (
    val isloading: Boolean = false,
    val readablechapter: Data? = null,
    val error: String = ""
        )