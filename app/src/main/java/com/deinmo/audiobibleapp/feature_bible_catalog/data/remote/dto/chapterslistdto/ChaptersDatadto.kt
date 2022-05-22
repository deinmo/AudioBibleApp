package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.chapterslistdto

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.ChaptersData
import com.google.gson.annotations.SerializedName


data class ChaptersDatadto (

  /*@SerializedName("id"           ) var id           : String?             = null,
  @SerializedName("bibleId"      ) var bibleId      : String?             = null,
  @SerializedName("abbreviation" ) var abbreviation : String?             = null,
  @SerializedName("name"         ) var name         : String?             = null,
  @SerializedName("nameLong"     ) var nameLong     : String?             = null,*/
  @SerializedName("chapters"     ) var chapters     : ArrayList<Chaptersdto> = arrayListOf()

)/*{
  fun toChaptersData(): ChaptersData {
    return ChaptersData(
     /* id = id,
      bibleId = bibleId,
      abbreviation = abbreviation,
      name = name,
      nameLong = nameLong,*/
      chapters = chapters.map { it.toChapters() }
    )
  }
}*/