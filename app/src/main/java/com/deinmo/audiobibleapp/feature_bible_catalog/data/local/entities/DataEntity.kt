package com.deinmo.audiobibleapp.feature_bible_catalog.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data

@Entity
data class DataEntity(
    @PrimaryKey var id : String,
    var bibleId    : String?,
    var number     : Int?,
    var bookId     : String?,
    var content    : String?,
    var verseCount : Int?
){
    fun toData(): Data {
        return Data(
            id = id,
            bibleId = bibleId,
            number = number,
            bookId = bookId,
            content = content,
            verseCount = verseCount,
            next = null,
            previous = null
        )
    }
}
