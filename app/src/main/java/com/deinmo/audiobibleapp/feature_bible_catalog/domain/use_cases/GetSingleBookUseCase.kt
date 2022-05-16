package com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases

import com.deinmo.audiobibleapp.core.Constants
import com.deinmo.audiobibleapp.core.Resource
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.BookData
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Chapters
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.ChaptersData
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.repository.BibleDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSingleBookUseCase @Inject constructor(
    val repository: BibleDataRepository
) {
    operator fun invoke(bible: String, bookid: String): Flow<Resource<List<Chapters>>> = flow{
        try{

            val chaptersdata = repository.getsinglebook(bible,bookid)
              val chapters =  chaptersdata.chapters.map{ it.toChapters()}
            emit(Resource.Loading())
            emit(Resource.Success(chapters))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't reach the server check your internet connection"))
        }
}
}