package com.deinmo.audiobibleapp.feature_bible_catalog.data.repository

import android.util.Log
import com.deinmo.audiobibleapp.core.Constants
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.Datadao
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.entities.DataEntity
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.BibleApi
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.biblebookdto.BooksDatadto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.bookdto.BookDatadto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.chapterslistdto.ChaptersDatadto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.chapterslistdto.Chaptersdto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.searchresponsedto.SearchResponsedto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.singlechapterdto.Datadto
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.repository.BibleDataRepository
import javax.inject.Inject

class BibleDataRepositoryImpl @Inject constructor(
    private val api: BibleApi,
    private val datadao: Datadao
): BibleDataRepository {
    override suspend fun getbooks(bibleid: String): List<BooksDatadto>? {
        var data = api.getbooks(bibleid).data
        return data
    }

    override suspend fun getsinglebook(bibleid: String,bookid: String): List<Chaptersdto>? {
        return api.getchapters(bibleid, bookid).data
    }

    override suspend fun getchapter(bibleid: String,chapterid: String): Datadto? {
        return api.getsinglechapter(bibleid, chapterid).data
    }

    override suspend fun insertchapter(dataEntity: DataEntity)  {
        return datadao.savechapterdata(dataEntity)
    }

    override suspend fun getsavedchapter(id : String?): DataEntity? {
       return datadao.getsaveddata(id)
    }

    override suspend fun getallchapters(): List<DataEntity>? {
        return datadao.getall()
    }

    override suspend fun getsearchedword(bibleid: String,query: String): SearchResponsedto? {
        return api.getsearchedword(bibleid, query)?.data
    }
}