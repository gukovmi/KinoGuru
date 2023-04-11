package com.shellwoo.kinoguru.app.di

import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.core.di.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class PresentationModule {

    companion object {

        @Provides
        fun provideViewModelFactory(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory =
            ViewModelFactory(creators)
    }
}