package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote

import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.chapterslistdto.ChapterList
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.singlechapterdto.SingleChapterdto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.biblebookdto.BibleBooks
import retrofit2.http.GET
import retrofit2.http.Path

interface BibleApi {
    @GET("v1/bibles/{bibleid}/books")
    suspend fun getbooks(@Path("bibleid") bibleid:String) : BibleBooks

    @GET("v1/bibles/{bibleid}/books/{bookid}/chapters")
    suspend fun getchapters(@Path("bibleid") bibleid:String,@Path("bookid") bookid:String) : ChapterList

    @GET("v1/bibles/{bibleid}/chapters/{chapterid}")
    suspend fun getsinglechapter(@Path("bibleid") bibleid:String,@Path("chapterid") chapterid:String) :SingleChapterdto
}