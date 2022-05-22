package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.biblebookdto

import com.google.gson.annotations.SerializedName


data class BibleBooks (

  @SerializedName("data" ) var data : ArrayList<BooksDatadto> = arrayListOf()

)