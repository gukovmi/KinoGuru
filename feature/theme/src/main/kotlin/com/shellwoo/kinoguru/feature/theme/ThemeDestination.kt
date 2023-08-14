package com.shellwoo.kinoguru.feature.theme

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shellwoo.kinoguru.feature.theme.ui.ThemeFragment

class ThemeDestination : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        ThemeFragment()
}