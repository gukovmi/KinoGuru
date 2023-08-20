package com.shellwoo.kinoguru.feature.search.di

import com.shellwoo.kinoguru.feature.search.ui.SearchFragment
import com.shellwoo.kinoguru.shared.language.di.LanguageDataComponent
import dagger.Component

@Component(
    dependencies = [SearchDeps::class, LanguageDataComponent::class],
    modules = [SearchPresentationModule::class]
)
internal interface SearchComponent {

    companion object {

        fun create(searchDeps: SearchDeps): SearchComponent =
            DaggerSearchComponent.builder()
                .searchDeps(searchDeps)
                .languageDataComponent(LanguageDataComponent.create())
                .build()
    }

    fun inject(fragment: SearchFragment)
}