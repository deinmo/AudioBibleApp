package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.singlechapterdto

import com.google.gson.annotations.SerializedName


data class Meta (

  @SerializedName("fums"          ) var fums          : String? = null,
  @SerializedName("fumsId"        ) var fumsId        : String? = null,
  @SerializedName("fumsJsInclude" ) var fumsJsInclude : String? = null,
  @SerializedName("fumsJs"        ) var fumsJs        : String? = null,
  @SerializedName("fumsNoScript"  ) var fumsNoScript  : String? = null

)