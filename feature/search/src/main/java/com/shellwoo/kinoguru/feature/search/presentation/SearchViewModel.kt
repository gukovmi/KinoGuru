package com.shellwoo.kinoguru.feature.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shellwoo.kinoguru.core.presentation.SingleLiveEvent
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

    private val _state = MutableLiveData<ScreenState>(ScreenState.Initial)
    val state: LiveData<ScreenState> = _state

    private val _onboardingEvent = SingleLiveEvent<Unit>()
    val onboardingEvent: LiveData<Unit> = _onboardingEvent

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
        val content = (_state.value as? ScreenState.Content) ?: return

        val queryChanged = query != content.query
        if (queryChanged) {
            search(query)
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            _state.value = (_state.value as? ScreenState.Content)?.copy(query = query, searchState = SearchState.Loading)

            val result = getSearchMovieResultUseCase(query)
            _state.value = (_state.value as? ScreenState.Content)?.copy(searchState = SearchState.Successful(result))
        }
    }
}