package com.shellwoo.kinoguru.shared.theme.data.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val THEME_DATA_STORE = "THEME_DATA_STORE"

val Context.themeDataStore by preferencesDataStore(THEME_DATA_STORE)