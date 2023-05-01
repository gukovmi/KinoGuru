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
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val isSearchOnboardingShowedUseCase: IsSearchOnboardingShowedUseCase,
    private val setSearchOnboardingShowedUseCase: SetSearchOnboardingShowedUseCase,
    private val getSearchMovieResultUseCase: GetSearchMovieResultUseCase,
) : ViewModel() {

    private companion object {

        val SEARCH_MOVIE_ITEMS_LOADING = List(20) { SearchMovieItem.Loading }
    }

    private val _state = MutableLiveData<ScreenState>(ScreenState.Initial)
    val state: LiveData<ScreenState> = _state

    private val _onboardingEvent = SingleLiveEvent<Unit>()
    val onboardingEvent: LiveData<Unit> = _onboardingEvent

    private val _searchErrorEvent = SingleLiveEvent<Unit>()
    val searchErrorEvent: LiveData<Unit> = _searchErrorEvent

    private val currentContentState: ScreenState.Content?
        get() = _state.value as? ScreenState.Content

    fun start() {
        _state.value = ScreenState.Content(query = "", SearchState.None)

        viewModelScope.launch { showSearchOnboardingIfNeeded() }
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

    fun setQuery(query: String) {
        val contentState = currentContentState ?: return

        val queryChanged = query != contentState.query
        if (queryChanged) {
            _state.value = contentState.copy(query = query)
            search()
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
}