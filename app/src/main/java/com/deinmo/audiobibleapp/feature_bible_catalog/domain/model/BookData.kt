package com.deinmo.audiobibleapp.feature_bible_catalog.domain.model

import com.google.gson.annotations.SerializedName

data class BookData(
    var id : String? = null,
    var bibleId: String? = null,
    var name : String? = null,
    var nameLong : String? = null,
)
