package com.shellwoo.kinoguru.feature.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shellwoo.kinoguru.feature.profile.ui.ProfileFragment

class ProfileDestination : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        ProfileFragment()
}
