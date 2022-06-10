package com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases

import com.deinmo.audiobibleapp.core.Resource
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.entities.DataEntity
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.repository.BibleDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSavedChapterUseCase @Inject constructor(
    val repository: BibleDataRepository
) {

    operator fun invoke(id: String?): Flow<Resource<Data>> = flow{
        try{
            emit(Resource.Loading())
            val data = repository.getsavedchapter(id)?.toData()
            emit(Resource.Success(data))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't reach the server check your internet connection"))
        }
    }
}