package com.shellwoo.kinoguru.feature.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shellwoo.kinoguru.core.coroutines.launchTrying
import com.shellwoo.kinoguru.core.presentation.SingleLiveEvent
import com.shellwoo.kinoguru.feature.search.domain.entity.SearchMovie
import com.shellwoo.kinoguru.feature.search.domain.usecase.GetSearchMovieResultUseCase
import com.shellwoo.kinoguru.feature.search.domain.usecase.IsSearchOnboardingShowedUseCase
import com.shellwoo.kinoguru.feature.search.domain.usecase.SetSearchOnboardingShowedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val isSearchOnboardingShowedUseCase: IsSearchOnboardingShowedUseCase,
    private val setSearchOnboardingShowedUseCase: SetSearchOnboardingShowedUseCase,
    private val getSearchMovieResultUseCase: GetSearchMovieResultUseCase,
) : ViewModel() {

    private companion object {

        const val SEARCH_QUERY_DEBOUNCE_IN_MILLIS = 500L
        const val SEARCH_QUERY_EMPTY = ""
        val SEARCH_MOVIE_ITEMS_LOADING = List(20) { SearchMovieItem.Loading }
    }

    private val _state = MutableLiveData<ScreenState>(ScreenState.Initial)
    val state: LiveData<ScreenState> = _state

    private val searchQuery = MutableStateFlow(SEARCH_QUERY_EMPTY)

    private val _onboardingEvent = SingleLiveEvent<Unit>()
    val onboardingEvent: LiveData<Unit> = _onboardingEvent

    private val _searchErrorEvent = SingleLiveEvent<Unit>()
    val searchErrorEvent: LiveData<Unit> = _searchErrorEvent

    private val currentContentState: ScreenState.Content?
        get() = _state.value as? ScreenState.Content

    fun start() {
        viewModelScope.launch { observeQuery() }
        _state.value = ScreenState.Content(query = SEARCH_QUERY_EMPTY, searchState = SearchState.None)

        viewModelScope.launch { showSearchOnboardingIfNeeded() }
    }

    private suspend fun observeQuery() {
        searchQuery
            .debounce(SEARCH_QUERY_DEBOUNCE_IN_MILLIS)
            .collect(::handleSearchQuery)
    }

    private fun handleSearchQuery(value: String) {
        if (value == SEARCH_QUERY_EMPTY) {
            _state.value = ScreenState.Content(query = SEARCH_QUERY_EMPTY, SearchState.None)
        } else {
            _state.value = currentContentState?.copy(query = value)
            search()
        }
    }

    private suspend fun showSearchOnboardingIfNeeded() {
        isSearchOnboardingShowedUseCase().collect { searchOnboardingShowed ->
            val searchOnboardingNeeded = !searchOnboardingShowed
            if (searchOnboardingNeeded) {
                _onboardingEvent(Unit)
                setSearchOnboardingShowedUseCase()
            }
        }
    }

    fun search() {
        viewModelScope.launchTrying(
            errorHandler = { handleSearchError() },
            block = {
                _state.value = currentContentState?.copy(searchState = SearchState.Result(SEARCH_MOVIE_ITEMS_LOADING))

                val query = currentContentState?.query ?: return@launchTrying
                val searchMovieResult = getSearchMovieResultUseCase(query)
                _state.value = currentContentState?.copy(
                    searchState = SearchState.Result(searchMovieResult.movies.toSearchMovieSuccessItems())
                )
            }
        )
    }

    private fun handleSearchError() {
        _state.value = currentContentState?.copy(searchState = SearchState.None)
        _searchErrorEvent(Unit)
    }

    private fun List<SearchMovie>.toSearchMovieSuccessItems(): List<SearchMovieItem.Success> =
        map(SearchMovieItem::Success)

    fun setQuery(query: String) {
        searchQuery.tryEmit(query)
    }
}