package com.shellwoo.kinoguru.feature.search.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.shellwoo.kinoguru.feature.search.data.datasource.SearchOnboardingShowingDataSourceImpl
import com.shellwoo.kinoguru.feature.search.data.datastore.onboardingShowingDataStore
import com.shellwoo.kinoguru.feature.search.data.repository.SearchOnboardingShowingRepositoryImpl
import com.shellwoo.kinoguru.shared.onboarding.data.datasource.OnboardingShowingDataSource
import com.shellwoo.kinoguru.shared.onboarding.domain.repository.OnboardingShowingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class SearchDataModule {

    companion object {

        @Provides
        fun provideOnboardingDataStore(context: Context): DataStore<Preferences> =
            context.onboardingShowingDataStore
    }

    @Binds
    abstract fun bindOnboardingShowingDataSource(impl: SearchOnboardingShowingDataSourceImpl): OnboardingShowingDataSource

    @Binds
    abstract fun bindOnboardingShowingRepository(impl: SearchOnboardingShowingRepositoryImpl): OnboardingShowingRepository
}