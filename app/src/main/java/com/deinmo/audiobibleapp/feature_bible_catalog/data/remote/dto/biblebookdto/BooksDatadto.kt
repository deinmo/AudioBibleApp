package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.biblebookdto

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.BookData
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.ChaptersData
import com.google.gson.annotations.SerializedName


data class BooksDatadto (

  @SerializedName("id"           ) var id           : String?             = null,
  @SerializedName("bibleId"      ) var bibleId      : String?             = null,
  @SerializedName("abbreviation" ) var abbreviation : String?             = null,
  @SerializedName("name"         ) var name         : String?             = null,
  @SerializedName("nameLong"     ) var nameLong     : String?             = null,
  @SerializedName("chapters"     ) var chapters     : ArrayList<Bookchapters> = arrayListOf()

){
  fun toBookData(): BookData {
    return BookData(
      id = id,
      bibleId = bibleId,
      name = name,
      nameLong = nameLong,
    )
  }
}