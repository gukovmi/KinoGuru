package com.shellwoo.kinoguru.feature.search.di

import com.shellwoo.kinoguru.feature.search.ui.SearchFragment
import dagger.Component

@Component(
    dependencies = [SearchDeps::class],
    modules = [SearchPresentationModule::class]
)
internal interface SearchComponent {

    companion object {

        fun create(searchDeps: SearchDeps): SearchComponent =
            DaggerSearchComponent.builder()
                .searchDeps(searchDeps)
                .build()
    }

    fun inject(fragment: SearchFragment)
}