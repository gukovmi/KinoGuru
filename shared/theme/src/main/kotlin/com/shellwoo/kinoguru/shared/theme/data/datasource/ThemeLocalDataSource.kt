package com.shellwoo.kinoguru.shared.theme.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThemeLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson,
) {

    private companion object {

        const val THEME_KEY = "THEME_KEY"
    }

    suspend fun set(theme: Theme) {
        dataStore.edit {
            it[stringPreferencesKey(THEME_KEY)] = gson.toJson(theme)
        }
    }

    suspend fun get(): Theme? =
        dataStore.data.map {
            val themeJson = it[stringPreferencesKey(THEME_KEY)]
            gson.fromJson(themeJson, Theme::class.java)
        }.first()
}