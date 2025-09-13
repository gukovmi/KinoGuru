package com.shellwoo.kinoguru.feature.main.presentation

data class MainState(
    val selectedTab: Tab,
    val tabs: List<Tab>
)

enum class Tab {
    SEARCH,
    PROFILE,
}