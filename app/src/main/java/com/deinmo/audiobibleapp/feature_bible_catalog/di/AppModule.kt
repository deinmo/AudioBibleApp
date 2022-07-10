package com.deinmo.audiobibleapp.feature_bible_catalog.di

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.room.Room
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.DataInfoDatabase
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.Datadao
import com.deinmo.audiobibleapp.feature_bible_catalog.data.remote.BibleApi
import com.deinmo.audiobibleapp.feature_bible_catalog.data.repository.BibleDataRepositoryImpl
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.repository.BibleDataRepository
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases.GetBooksUseCase
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases.GetSingleBookUseCase
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases.GetSingleChapterUseCase
import com.deinmo.audiobibleapp.feature_profile.data.repository.ProfileRepositoryImpl
import com.deinmo.audiobibleapp.feature_profile.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    class TokenInterceptor: Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            var url: Request = chain.request()
            url =  url.newBuilder().addHeader("api-key","c9a2280b85d3b51d98cfdd1a08f60f5e").build()
            return chain.proceed(url)
        }
    }

    @Singleton
    @Provides
    fun providettspeech(app: Application): TextToSpeech? {
       var tts: TextToSpeech? = null
       tts = TextToSpeech(app, TextToSpeech.OnInitListener { textt ->
            if (textt == TextToSpeech.SUCCESS){
                 tts?.language = Locale.UK
            }
        })
        return tts
    }

    @Provides
    @Singleton
    fun ProvidebibleApi(): BibleApi{
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        var client = OkHttpClient.Builder().addInterceptor(TokenInterceptor()).addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl("https://api.scripture.api.bible/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(BibleApi::class.java)
    }

    @Provides
    @Singleton
    fun Providebibledatabase(app: Application): DataInfoDatabase{
        return Room.databaseBuilder(app, DataInfoDatabase::class.java, "bible_db").build()
    }

    @Provides
    @Singleton
    fun providebiblerepository(
        api: BibleApi,
        db: DataInfoDatabase
    ): BibleDataRepository{
        return BibleDataRepositoryImpl(api,db.datadao)
    }

    @Provides
    @Singleton
    fun provideprofilerepo(
        auth: FirebaseAuth
    ): ProfileRepository {
        return ProfileRepositoryImpl(auth)
    }

    @Provides
    @Singleton
    fun providefirebaseinstane(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
   /* @Provides
    @Singleton
    fun providegetbooksusecase(repository: BibleDataRepository): GetBooksUseCase{
        return GetBooksUseCase(repository)
    }

    @Provides
    @Singleton
    fun providegetchapterslistusecase(repository: BibleDataRepository): GetSingleBookUseCase {
        return GetSingleBookUseCase(repository)
    }

    @Provides
    @Singleton
    fun providegetchaptersusecase(repository: BibleDataRepository): GetSingleChapterUseCase {
        return GetSingleChapterUseCase(repository)
    }*/
}

