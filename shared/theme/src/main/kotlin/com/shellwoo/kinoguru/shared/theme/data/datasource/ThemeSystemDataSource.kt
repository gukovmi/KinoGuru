package com.shellwoo.kinoguru.shared.theme.data.datasource

import androidx.appcompat.app.AppCompatDelegate
import com.shellwoo.kinoguru.shared.theme.data.converter.ThemeConverter
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import javax.inject.Inject

class ThemeSystemDataSource @Inject constructor(private val converter: ThemeConverter) {

    fun get(): Theme {
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        return converter.fromNightMode(nightMode)
    }

    fun set(theme: Theme) {
        val nightMode = converter.toNightMode(theme)
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }
}