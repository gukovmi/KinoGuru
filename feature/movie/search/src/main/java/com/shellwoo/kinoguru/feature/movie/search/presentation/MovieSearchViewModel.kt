package com.shellwoo.kinoguru.feature.movie.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shellwoo.kinoguru.core.coroutines.launchTrying
import com.shellwoo.kinoguru.core.presentation.SingleLiveEvent
import com.shellwoo.kinoguru.feature.movie.search.domain.entity.MovieSearch
import com.shellwoo.kinoguru.feature.movie.search.domain.scenario.GetMovieSearchResultScenario
import com.shellwoo.kinoguru.feature.movie.search.domain.usecase.IsMovieSearchOnboardingShowedUseCase
import com.shellwoo.kinoguru.feature.movie.search.domain.usecase.SetMovieSearchOnboardingShowedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieSearchViewModel @Inject constructor(
    private val isMovieSearchOnboardingShowedUseCase: IsMovieSearchOnboardingShowedUseCase,
    private val setMovieSearchOnboardingShowedUseCase: SetMovieSearchOnboardingShowedUseCase,
    private val getMovieSearchResultScenario: GetMovieSearchResultScenario,
    private val router: MovieSearchRouter,
) : ViewModel() {

    private companion object {

        const val SEARCH_QUERY_DEBOUNCE_IN_MILLIS = 500L
        const val SEARCH_QUERY_EMPTY = ""
        val SEARCH_MOVIE_ITEMS_LOADING = List(20) { MovieSearchItem.Loading }
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
        isMovieSearchOnboardingShowedUseCase().collect { searchOnboardingShowed ->
            val searchOnboardingNeeded = !searchOnboardingShowed
            if (searchOnboardingNeeded) {
                _onboardingEvent(Unit)
                setMovieSearchOnboardingShowedUseCase()
            }
        }
    }

    fun search() {
        viewModelScope.launchTrying(
            errorHandler = { handleSearchError() },
            block = {
                _state.value = currentContentState?.copy(searchState = SearchState.Result(SEARCH_MOVIE_ITEMS_LOADING))

                val query = currentContentState?.query ?: return@launchTrying
                val searchMovieResult = getMovieSearchResultScenario(query)
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

    private fun List<MovieSearch>.toSearchMovieSuccessItems(): List<MovieSearchItem.Success> =
        map(MovieSearchItem::Success)

    fun setQuery(query: String) {
        searchQuery.tryEmit(query)
    }

    fun selectMovieSuccessItem(item: MovieSearchItem.Success) {
        router.openMovieDetailsScreen(item.value.id)
    }
}