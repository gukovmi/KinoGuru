package com.shellwoo.kinoguru.shared.theme.data.converter

import androidx.appcompat.app.AppCompatDelegate
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import javax.inject.Inject

class ThemeConverter @Inject constructor() {

    fun fromNightMode(nightMode: Int): Theme =
        when (nightMode) {
            AppCompatDelegate.MODE_NIGHT_NO -> Theme.LIGHT
            AppCompatDelegate.MODE_NIGHT_YES -> Theme.DARK
            AppCompatDelegate.MODE_NIGHT_AUTO_TIME -> Theme.AUTO_TIME
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> Theme.FOLLOW_SYSTEM
            AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY -> Theme.AUTO_BATTERY
            else -> Theme.UNSPECIFIED
        }

    fun toNightMode(theme: Theme): Int =
        when (theme) {
            Theme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            Theme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
            Theme.AUTO_TIME -> AppCompatDelegate.MODE_NIGHT_AUTO_TIME
            Theme.FOLLOW_SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            Theme.AUTO_BATTERY -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            Theme.UNSPECIFIED -> AppCompatDelegate.MODE_NIGHT_UNSPECIFIED
        }
}