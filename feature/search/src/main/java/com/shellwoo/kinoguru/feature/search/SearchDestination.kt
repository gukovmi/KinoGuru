package com.shellwoo.kinoguru.feature.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shellwoo.kinoguru.feature.search.ui.SearchFragment

class SearchDestination : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        SearchFragment()
}