package com.deinmo.audiobibleapp.feature_bible_catalog.presentation.search_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deinmo.audiobibleapp.core.Constants
import com.deinmo.audiobibleapp.core.Resource
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.repository.BibleDataRepository
import com.deinmo.audiobibleapp.feature_bible_catalog.domain.use_cases.GetSearchedWordUseCase
import com.deinmo.audiobibleapp.home_screen.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchedScreenViewModel @Inject constructor(
    private val getSearchedWordUseCase: GetSearchedWordUseCase,
    private val repository: BibleDataRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf(SearchScreenState())
    val state: State<SearchScreenState> = _state

    init {
        var content: String? = savedStateHandle.get<String>("query")
        Log.d("message",content!!)
        getsearchedwordlist(content!!)
    }
    fun getsearchedwordlist(query: String) {
        getSearchedWordUseCase(Constants.bibleid, query).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _state.value = SearchScreenState(isloading = true)
                }
                is Resource.Success -> {
                    _state.value = SearchScreenState(books = resource.data!!)
                }
                is Resource.Error -> {
                    _state.value =
                        SearchScreenState(error = resource.message ?: "An unexpected error occured")
                }
            }
        }.launchIn(viewModelScope)
    }
}
