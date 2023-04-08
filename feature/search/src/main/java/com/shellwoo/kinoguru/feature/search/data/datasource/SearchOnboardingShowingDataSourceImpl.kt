package com.shellwoo.kinoguru.feature.search.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.shellwoo.kinoguru.shared.onboarding.data.datasource.OnboardingShowingDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchOnboardingShowingDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : OnboardingShowingDataSource {

    private companion object {

        const val SEARCH_MOVIE_ONBOARDING_SHOWED = "SEARCH_MOVIE_ONBOARDING_SHOWED"
    }

    override fun isShowed(): Flow<Boolean> =
        dataStore.data.map {
            it[booleanPreferencesKey(SEARCH_MOVIE_ONBOARDING_SHOWED)] ?: false
        }

    override suspend fun setShowed() {
        dataStore.edit {
            it[booleanPreferencesKey(SEARCH_MOVIE_ONBOARDING_SHOWED)] = true
        }
    }
}