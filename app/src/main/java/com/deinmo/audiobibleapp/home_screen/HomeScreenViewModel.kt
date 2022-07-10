package com.deinmo.audiobibleapp.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deinmo.audiobibleapp.core.Constants
import com.deinmo.audiobibleapp.core.Resource
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.model.Data
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.repository.BibleDataRepository
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases.GetSearchedWordUseCase
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.biblebooks.BibleListState
import com.deinmo.audiobibleapp.feature_bible_catalog.presentation.bible_canvas.singlechapter.SingleChapterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getSearchedWordUseCase: GetSearchedWordUseCase,
    private val repository: BibleDataRepository
): ViewModel() {
    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    fun getsearchedwordlist(query: String): List<Data> {
        getSearchedWordUseCase(Constants.bibleid, query).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isloading = true)
                }
                is Resource.Success -> {
                    _state.value = HomeScreenState(books = resource.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        HomeScreenState(error = resource.message ?: "An unexpected error occured")
                }
            }
        }.launchIn(viewModelScope)
        return state.value.books
    }
}

