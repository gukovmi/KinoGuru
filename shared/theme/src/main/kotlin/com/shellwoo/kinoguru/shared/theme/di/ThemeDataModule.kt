package com.shellwoo.kinoguru.shared.theme.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.gson.Gson
import com.shellwoo.kinoguru.shared.theme.data.datastore.themeDataStore
import com.shellwoo.kinoguru.shared.theme.data.repository.ThemeRepositoryImpl
import com.shellwoo.kinoguru.shared.theme.domain.repository.ThemeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ThemeDataModule {

    companion object {

        @Provides
        fun provideGson(): Gson =
            Gson()

        @Provides
        fun provideThemeDataStore(context: Context): DataStore<Preferences> =
            context.themeDataStore
    }

    @Binds
    abstract fun bindsThemeRepository(impl: ThemeRepositoryImpl): ThemeRepository
}