package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.search_screen

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data

data class SearchScreenState(
    var isloading: Boolean = false,
    var books: List<Data> = emptyList(),
    val error: String = ""
)
