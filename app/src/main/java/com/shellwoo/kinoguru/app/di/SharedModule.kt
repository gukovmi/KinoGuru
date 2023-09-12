package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.shared.error.di.ErrorModule
import dagger.Module

@Module(
    includes = [
        ErrorModule::class
    ]
)
interface SharedModule