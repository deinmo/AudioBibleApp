package com.deinmo.audiobibleapp.feature_bible_catalog.domain.repository

import com.deinmo.audiobibleapp.core.Resource
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.entities.DataEntity
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.biblebookdto.BooksDatadto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.bookdto.BookDatadto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.chapterslistdto.ChaptersDatadto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.chapterslistdto.Chaptersdto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.singlechapterdto.Datadto
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.BookData
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.ChaptersData
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data
import kotlinx.coroutines.flow.Flow

interface BibleDataRepository {
    suspend fun getbooks(bibleid: String): List<BooksDatadto>?

    suspend fun getsinglebook(bibleid: String,bookid: String): List<Chaptersdto>?

    suspend fun getchapter(bibleid: String,chapterid: String): Datadto?

    suspend fun insertchapter(dataEntity: DataEntity)

    suspend fun getsavedchapter(id: String?):DataEntity?

    suspend fun getallchapters(): List<DataEntity>?
}