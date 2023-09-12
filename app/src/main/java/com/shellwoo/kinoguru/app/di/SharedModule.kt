package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.shared.error.di.ErrorModule
import com.shellwoo.kinoguru.shared.theme.di.ThemeDataModule
import com.shellwoo.kinoguru.shared.user.di.UserDataModule
import dagger.Module

@Module(
    includes = [
        ErrorModule::class,
        ThemeDataModule::class,
        UserDataModule::class,
    ]
)
interface SharedModule