package com.shellwoo.kinoguru.feature.splash

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shellwoo.kinoguru.feature.splash.ui.SplashFragment

object SplashDestination : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        SplashFragment()
}