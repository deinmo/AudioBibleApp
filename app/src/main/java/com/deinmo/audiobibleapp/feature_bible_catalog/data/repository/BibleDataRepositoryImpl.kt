package com.deinmo.audiobibleapp.feature_bible_catalog.data.repository

import com.deinmo.audiobibleapp.core.Constants
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.Datadao
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.entities.DataEntity
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.BibleApi
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.bookdto.BookDatadto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.chapterslistdto.ChaptersDatadto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.singlechapterdto.Datadto
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.repository.BibleDataRepository
import javax.inject.Inject

class BibleDataRepositoryImpl @Inject constructor(
    private val api: BibleApi
): BibleDataRepository {
    override suspend fun getbooks(bibleid: String): List<BookDatadto> {
        return api.getbooks(bibleid)
    }

    override suspend fun getsinglebook(bibleid: String,bookid: String): ChaptersDatadto {
        return api.getchapters(bibleid, bookid)
    }

    override suspend fun getchapter(bibleid: String,chapterid: String): Datadto? {
        return api.getsinglechapter(bibleid, chapterid).data
    }

   /* override suspend fun insertchapter(dataEntity: DataEntity) {
        datadao.savechapterdata(dataEntity)
    }*/

}