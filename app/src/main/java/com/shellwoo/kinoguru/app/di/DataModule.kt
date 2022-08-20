package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.feature.login.di.FirebaseAuthModule
import com.shellwoo.kinoguru.shared.user.di.UserDataModule
import dagger.Module

@Module(
    includes = [
        FirebaseAuthModule::class,
        UserDataModule::class,
    ]
)
interface DataModule