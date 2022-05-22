package com.deinmo.audiobibleapp.feature_bible_catalog.domain.model

data class ChaptersData(
    var id: String?,
    var bibleId: String?,
    var abbreviation: String?,
    var name: String?,
    var nameLong: String?,
    var chapters: List<Chapter>
)