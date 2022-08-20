package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.feature.login.di.FirebaseAuthModule
import dagger.Module

@Module(
    includes = [
        FirebaseAuthModule::class,
    ]
)
interface DataModule