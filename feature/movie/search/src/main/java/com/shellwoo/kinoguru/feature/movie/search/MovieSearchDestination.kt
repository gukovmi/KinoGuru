package com.shellwoo.kinoguru.feature.movie.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shellwoo.kinoguru.feature.movie.search.ui.MovieSearchFragment

class MovieSearchDestination : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        MovieSearchFragment()
}