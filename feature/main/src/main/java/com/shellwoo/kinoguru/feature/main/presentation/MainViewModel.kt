package com.shellwoo.kinoguru.feature.main.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: MainRouter,
) : ViewModel() {
    private val _state = MutableLiveData(MainState(Tab.SEARCH, Tab.entries))
    val state: LiveData<MainState> = _state

    init {
        router.openSearchScreen()
    }

    fun selectTab(tab: Tab) {
        val selectedTab = _state.value?.selectedTab ?: return

        if (selectedTab != tab) {
            _state.value = _state.value?.copy(selectedTab = tab)

            when (tab) {
                Tab.SEARCH -> router.openSearchScreen()
                Tab.PROFILE -> router.openProfileScreen()
            }
        }
    }
}