package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.singlechapterdto

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data
import com.google.gson.annotations.SerializedName


data class Datadto (

  @SerializedName("id"         ) var id         : String?   = null,
  @SerializedName("bibleId"    ) var bibleId    : String?   = null,
  @SerializedName("number"     ) var number     : String?   = null,
  @SerializedName("bookId"     ) var bookId     : String?   = null,
  @SerializedName("content"    ) var content    : String?   = null,
  @SerializedName("reference"  ) var reference  : String?   = null,
  @SerializedName("verseCount" ) var verseCount : Int?      = null,
  @SerializedName("next"       ) var next       : Next?     = Next(),
  @SerializedName("previous"   ) var previous   : Previous? = Previous(),
  @SerializedName("copyright"  ) var copyright  : String?   = null

){
  fun toData(): Data {
    return Data(
      id = id,
      bibleId = bibleId,
      number = number?.toInt(),
      bookId = bookId,
      content = content,
      verseCount = verseCount,
      next = next,
      previous = previous
    )
  }
}