package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.chapterslistdto

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Chapters
import com.google.gson.annotations.SerializedName


data class Chaptersdto (

  @SerializedName("id"        ) var id        : String? = null,
  @SerializedName("bibleId"   ) var bibleId   : String? = null,
  @SerializedName("number"    ) var number    : String? = null,
  @SerializedName("bookId"    ) var bookId    : String? = null,
  @SerializedName("reference" ) var reference : String? = null

){
  fun toChapters(): Chapters {
    return Chapters(
      id = id,
      bibleid = bibleId,
      number = number?.toInt(),
      bookid = bookId
    )
  }
}