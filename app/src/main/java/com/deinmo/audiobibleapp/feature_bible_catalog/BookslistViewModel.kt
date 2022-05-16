package com.deinmo.audiobibleapp.feature_bible_catalog

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deinmo.audiobibleapp.core.Resource
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookslistViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
)  : ViewModel(){
    private val _state = mutableStateOf(BibleListState())
    val state: State<BibleListState> = _state

    init {
        getbooks()
    }
    fun getbooks(){
        getBooksUseCase().onEach {resource ->
            when(resource){
                is Resource.Loading -> {
                    _state.value = BibleListState(isloading = true)
                }
                is Resource.Success -> {
                    _state.value = BibleListState(books = resource.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = BibleListState(error = resource.message ?: "An unexpected error occured")
                }
            }
        }.launchIn(viewModelScope)
    }
}