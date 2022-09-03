package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.searchresponsedto

import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data
import com.google.gson.annotations.SerializedName

data class SearchVersedto(
    @SerializedName("id") var id: String? = null,
    @SerializedName("orgId") var orgId: String? = null,
    @SerializedName("bibleId") var bibleid: String? = null,
    @SerializedName("bookId") var bookid: String? = null,
    @SerializedName("chapterId") var chapterId: String? = null,
    @SerializedName("text") var text: String? = null,
    @SerializedName("reference") var reference: String? = null
){
    fun toData(): Data {
        return Data(
            id = id,
            bibleId = bibleid,
            number = null,
            bookId = chapterId,
            content = text,
            verseCount = null,
            next = null,
            previous = null,
            reference = reference
        )
    }
}
