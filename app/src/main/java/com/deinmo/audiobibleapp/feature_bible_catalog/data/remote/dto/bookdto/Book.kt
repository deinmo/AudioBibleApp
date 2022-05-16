package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.bookdto

import com.google.gson.annotations.SerializedName


data class Book (

  @SerializedName("data" ) var data : ArrayList<BookDatadto> = arrayListOf()

)