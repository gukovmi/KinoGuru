package com.shellwoo.kinoguru.core.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelFactoryModule {

    @Provides
    fun provideViewModelFactory(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory =
        ViewModelFactory(creators)
}