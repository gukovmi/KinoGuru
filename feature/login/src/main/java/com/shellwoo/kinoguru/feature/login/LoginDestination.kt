package com.shellwoo.kinoguru.feature.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shellwoo.kinoguru.feature.login.ui.LoginFragment

class LoginDestination : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        LoginFragment()
}