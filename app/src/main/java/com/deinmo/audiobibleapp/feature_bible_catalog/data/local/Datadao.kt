package com.deinmo.audiobibleapp.feature_bible_catalog.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.entities.DataEntity
import dagger.Provides
import kotlinx.coroutines.flow.Flow

@Dao
interface Datadao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savechapterdata(data: DataEntity)

    @Query("SELECT * FROM dataentity")
    suspend fun getall(): List<DataEntity>

    @Query("SELECT * FROM dataentity WHERE id = :id")
    suspend fun getsaveddata(id: String?): DataEntity?
}