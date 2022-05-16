package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.singlechapterdto

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Next_and_Previous
import com.google.gson.annotations.SerializedName


data class Next (

  @SerializedName("id"     ) var id     : String? = null,
  @SerializedName("bookId" ) var bookId : String? = null,
  @SerializedName("number" ) var number : String? = null

){
  fun toNextandPrevious(): Next_and_Previous {
    return Next_and_Previous(
      id = id,
      bookId = bookId,
      number = number?.toInt()
    )
  }
}