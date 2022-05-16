package com.deinmo.audiobibleapp.feature_bible_catalog.domain.model

import com.google.gson.annotations.SerializedName

data class Next_and_Previous(
    var id     : String?,
    var bookId : String?,
    var number : Int?
)
