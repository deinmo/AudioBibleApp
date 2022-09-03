package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.searchresponsedto

import com.google.gson.annotations.SerializedName

data class SearchedWordResponsedto(
    @SerializedName("data") var data: SearchResponsedto,
)
