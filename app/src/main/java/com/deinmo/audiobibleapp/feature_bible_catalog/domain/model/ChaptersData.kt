package com.deinmo.audiobibleapp.feature_bible_catalog.domain.model

import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.chapterslistdto.Chaptersdto
import com.google.gson.annotations.SerializedName

data class ChaptersData(
    var id: String?,
    var bibleId: String?,
    var abbreviation: String?,
    var name: String?,
    var nameLong: String?,
    var chapters: List<Chapters>
)