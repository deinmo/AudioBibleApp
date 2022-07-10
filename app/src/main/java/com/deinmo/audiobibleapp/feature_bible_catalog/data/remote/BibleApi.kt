package com.deinmo.audiobibleapp.feature_bible_catalog.data.remote

import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.chapterslistdto.ChapterList
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.singlechapterdto.SingleChapterdto
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.biblebookdto.BibleBooks
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.dto.searchresponsedto.SearchResponsedto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BibleApi {
    @GET("v1/bibles/{bibleid}/books")
    suspend fun getbooks(@Path("bibleid") bibleid:String) : BibleBooks

    @GET("v1/bibles/{bibleid}/books/{bookid}/chapters")
    suspend fun getchapters(@Path("bibleid") bibleid:String,@Path("bookid") bookid:String) : ChapterList

    @GET("v1/bibles/{bibleid}/chapters/{chapterid}")
    suspend fun getsinglechapter(@Path("bibleid") bibleid:String,@Path("chapterid") chapterid:String) :SingleChapterdto

    @GET("v1/bibles/{bibleid}/search")
    suspend fun getsearchedword(@Path("bibleid") bibleid: String,@Query("query") queryString: String):SearchResponsedto
}