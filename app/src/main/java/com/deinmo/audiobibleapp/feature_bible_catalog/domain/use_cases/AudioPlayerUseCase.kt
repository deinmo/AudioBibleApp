package com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases

import android.speech.tts.TextToSpeech
import android.util.Log
import com.deinmo.audiobibleapp.core.Resource
import com.deinmo.audiobibleapp.feature_bible_catalog.data.local.entities.DataEntity
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.repository.BibleDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AudioPlayerUseCase @Inject constructor(
    val tts: TextToSpeech?
){
    fun playaudio(content: String){
        if (!tts?.isSpeaking!!){
            Log.d("result","is not speaking")
            tts?.speak(content, TextToSpeech.QUEUE_FLUSH, null, "")
        }else{
            Log.d("result","is speaking")
            tts?.stop()
        }
    }

}