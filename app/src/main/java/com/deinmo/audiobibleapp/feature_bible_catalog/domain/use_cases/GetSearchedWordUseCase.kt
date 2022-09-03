package com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases

import android.util.Log
import com.deinmo.audiobibleapp.core.Resource
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.repository.BibleDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSearchedWordUseCase @Inject constructor(
    val repository: BibleDataRepository
)  {
    operator fun invoke(bibleid: String, query: String): Flow<Resource<List<Data>>> = flow{
        try{
            emit(Resource.Loading())
            val response = repository.getsearchedword(bibleid,query)
            val data = response?.verses?.map { it.toData() }
            Log.d("message", response?.query!!)
            emit(Resource.Success(data))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e: IOException){
            emit(Resource.Error("Couldn't reach the server check your internet connection"))
        }
    }
}