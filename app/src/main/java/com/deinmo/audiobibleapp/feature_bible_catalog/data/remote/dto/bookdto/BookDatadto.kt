package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.bookdto

import androidx.compose.runtime.internal.illegalDecoyCallException
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.BookData
import com.google.gson.annotations.SerializedName


data class BookDatadto (

  @SerializedName("id"        ) var id        : String? = null,
  @SerializedName("bibleId"   ) var bibleId   : String? = null,
  @SerializedName("number"    ) var number    : String? = null,
  @SerializedName("bookId"    ) var bookId    : String? = null,
  @SerializedName("reference" ) var reference : String? = null

){

fun toBookData(): BookData {
  return BookData(
    id = id,
    bibleid = bibleId,
    number = number?.toInt(),
    bookid = bookId
  )
}
}