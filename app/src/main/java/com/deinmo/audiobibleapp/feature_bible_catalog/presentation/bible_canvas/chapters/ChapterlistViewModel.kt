package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.chapters

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deinmo.audiobibleapp.core.Resource
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases.GetSingleBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class ChapterlistViewModel@Inject constructor(
    private val getSingleBooksUseCase: GetSingleBookUseCase,
    savedStateHandle: SavedStateHandle
)  : ViewModel() {
    private val _state = mutableStateOf(ChapterListState())
    val state: State<ChapterListState> = _state

    init {
        val bibleid: String? = savedStateHandle.get<String>("bibleid")
        val bookid: String? = savedStateHandle.get<String>("bookid")
        bibleid?.let { bookid?.let { it1 -> getchapters(it, it1) } }
    }
    fun getchapters(bibleid: String, bookid: String){

    getSingleBooksUseCase(bibleid, bookid).onEach { resource ->
        when(resource){
            is Resource.Loading -> {
                _state.value = ChapterListState(isloading = true)
            }
            is Resource.Success -> {
                _state.value = ChapterListState(books = resource.data ?: emptyList())
            }
            is Resource.Error -> {
                _state.value = ChapterListState(error = resource.message ?: "An unexpected error occured")
            }
        }
    }.launchIn(viewModelScope)
    }
}