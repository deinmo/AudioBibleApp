package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.biblebookdto

import com.google.gson.annotations.SerializedName


data class Bookchapters (

  @SerializedName("id"        ) var id        : String? = null,
  @SerializedName("bibleId"   ) var bibleId   : String? = null,
  @SerializedName("number"    ) var number    : String? = null,
  @SerializedName("bookId"    ) var bookId    : String? = null,
  @SerializedName("reference" ) var reference : String? = null

)