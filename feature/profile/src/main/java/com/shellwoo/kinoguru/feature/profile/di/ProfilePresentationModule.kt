package com.shellwoo.kinoguru.feature.profile.di

import androidx.lifecycle.ViewModel
import com.shellwoo.kinoguru.core.di.ViewModelFactoryModule
import com.shellwoo.kinoguru.core.di.ViewModelKey
import com.shellwoo.kinoguru.feature.profile.presentation.ProfileViewModel
import com.shellwoo.kinoguru.shared.theme.di.ThemeDataModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ThemeDataModule::class, ViewModelFactoryModule::class])
internal interface ProfilePresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}