package com.shellwoo.kinoguru.feature.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shellwoo.kinoguru.feature.main.ui.MainFragment

object MainDestination : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        MainFragment()
}