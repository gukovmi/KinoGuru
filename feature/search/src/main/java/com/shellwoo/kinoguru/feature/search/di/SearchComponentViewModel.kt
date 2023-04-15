package com.shellwoo.kinoguru.feature.search.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

internal class SearchComponentViewModel(app: Application) : AndroidViewModel(app) {

    val component: SearchComponent by lazy {
        SearchComponent.create((app as SearchDepsProvider).searchDeps)
    }
}