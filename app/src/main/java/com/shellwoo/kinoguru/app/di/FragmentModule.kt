package com.shellwoo.kinoguru.app.di

import com.shellwoo.kinoguru.feature.search.di.SearchFragmentModule
import dagger.Module

@Module(
    includes = [
        SearchFragmentModule::class,
    ]
)
interface FragmentModule