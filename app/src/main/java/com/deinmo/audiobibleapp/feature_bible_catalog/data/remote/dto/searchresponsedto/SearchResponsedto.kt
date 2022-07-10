package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.searchresponsedto

import com.google.gson.annotations.SerializedName

data class SearchResponsedto(
    @SerializedName("query") var query: String,
    @SerializedName("limit") var limit: Int,
    @SerializedName("offset") var offset: Int,
    @SerializedName("total") var total: Int,
    @SerializedName("verseCount") var verseCount: Int,
    @SerializedName("verses") var verses: ArrayList<SearchVersedto> = arrayListOf()
)
