package com.deinmo.audiobibleapp.feature_profile.presentation

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.BookData
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data

data class ProfileState (
    val isloading: Boolean = false,
    val books: List<Data> = emptyList(),
    val error: String = ""
)