package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.chapterslistdto

import com.google.gson.annotations.SerializedName


data class ChapterList (

  @SerializedName("data" ) var data : ArrayList<ChaptersDatadto> = arrayListOf()

)