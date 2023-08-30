package com.shellwoo.kinoguru.feature.movie.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shellwoo.kinoguru.feature.movie.detail.ui.MovieDetailsFragment

class MovieDetailsDestination(private val movieId: Int) : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        MovieDetailsFragment.newInstance(movieId)
}