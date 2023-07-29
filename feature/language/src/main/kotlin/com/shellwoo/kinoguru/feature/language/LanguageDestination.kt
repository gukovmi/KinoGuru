package com.shellwoo.kinoguru.feature.language

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shellwoo.kinoguru.feature.language.ui.LanguageFragment

class LanguageDestination : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        LanguageFragment()
}