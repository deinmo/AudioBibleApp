package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.singlechapterdto

import com.google.gson.annotations.SerializedName


data class SingleChapterdto (

    @SerializedName("data" ) var data : Datadto? = Datadto(),
    @SerializedName("meta" ) var meta : Meta? = Meta()

)