package com.deinmo.audiobibleapp.feature_bible_catalog.domain.model

import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.singlechapterdto.Next
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.singlechapterdto.Previous
import com.google.gson.annotations.SerializedName

data class Data(
    var id         : String?,
    var bibleId    : String?,
    var number     : Int?,
    var bookId     : String?,
    var content    : String?,
    var verseCount : Int?,
    var next       : Next?,
    var previous   : Previous?,
)
