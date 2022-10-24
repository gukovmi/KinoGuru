package com.shellwoo.kinoguru.feature.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.core.presentation.SingleLiveEvent
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<SearchState>(SearchState.Initial)
    val state: LiveData<SearchState> = _state

    private val _onboardingEvent = SingleLiveEvent<Unit>()
    val onboardingEvent: LiveData<Unit> = _onboardingEvent

    fun start() {
        _state.value = SearchState.Content
        _onboardingEvent(Unit)
    }
}