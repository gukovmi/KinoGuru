package com.shellwoo.kinoguru.feature.search.di

import com.shellwoo.kinoguru.feature.search.ui.SearchFragment
import com.shellwoo.kinoguru.shared.language.di.LanguageDataModule
import dagger.Component

@Component(
    dependencies = [SearchDeps::class],
    modules = [SearchPresentationModule::class, LanguageDataModule::class]
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