package com.deinmo.audiobibleapp.home_screen

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data

data class HomeScreenState(
    var isloading: Boolean = false,
    var books: List<Data> = emptyList(),
    val error: String = ""
)
