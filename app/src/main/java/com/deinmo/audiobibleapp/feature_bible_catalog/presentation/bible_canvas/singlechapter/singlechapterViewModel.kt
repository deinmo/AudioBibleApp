package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.singlechapter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deinmo.audiobibleapp.core.Resource
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases.GetSingleChapterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class singlechapterViewModel @Inject constructor(
    private val getSingleChapterUseCase: GetSingleChapterUseCase,
    savedStateHandle: SavedStateHandle
)  : ViewModel(){
    private val _state = mutableStateOf(SingleChapterState())
    val state: State<SingleChapterState> = _state

    init {
        val bibleid: String? = savedStateHandle.get<String>("bibleid")
        val chapterid: String? = savedStateHandle.get<String>("chapterid")
        bibleid?.let { chapterid?.let { it1 -> getchapters(it, it1) } }
    }
    fun getchapters(bibleid: String, chapterid: String){

        getSingleChapterUseCase(bibleid,chapterid).onEach {result ->
            when(result){
                is Resource.Error -> {
                    _state.value = SingleChapterState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Success -> {
                    _state.value = SingleChapterState(readablechapter = result.data)
                }
                is Resource.Loading ->{
                    _state.value = SingleChapterState(isloading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}