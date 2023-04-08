package com.shellwoo.kinoguru.feature.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shellwoo.kinoguru.core.presentation.SingleLiveEvent
import com.shellwoo.kinoguru.feature.search.domain.usecase.IsSearchOnboardingShowedUseCase
import com.shellwoo.kinoguru.feature.search.domain.usecase.SetSearchOnboardingShowedUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val isSearchOnboardingShowedUseCase: IsSearchOnboardingShowedUseCase,
    private val setSearchOnboardingShowedUseCase: SetSearchOnboardingShowedUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<SearchState>(SearchState.Initial)
    val state: LiveData<SearchState> = _state

    private val _onboardingEvent = SingleLiveEvent<Unit>()
    val onboardingEvent: LiveData<Unit> = _onboardingEvent

    fun start() {
        _state.value = SearchState.Content

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
}