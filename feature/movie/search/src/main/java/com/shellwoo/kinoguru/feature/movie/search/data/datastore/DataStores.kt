package com.shellwoo.kinoguru.feature.movie.search.data.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val ONBOARDING_SHOWING_DATA_STORE = "ONBOARDING_SHOWING_DATA_STORE"

val Context.onboardingShowingDataStore by preferencesDataStore(ONBOARDING_SHOWING_DATA_STORE)