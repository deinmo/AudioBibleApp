package com.deinmo.audiobibleapp.feature_bible_catalog.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.entities.DataEntity

@Database(
    entities = [DataEntity::class],
    version = 1
)
abstract class DataInfoDatabase: RoomDatabase() {
    abstract val datadao: Datadao
}