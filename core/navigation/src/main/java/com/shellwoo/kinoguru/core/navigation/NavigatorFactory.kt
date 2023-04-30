package com.shellwoo.kinoguru.core.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.shellwoo.kinoguru.core.ui.component.FragmentTransactionAnimator
import javax.inject.Inject

class NavigatorFactory @Inject constructor(private val fragmentTransactionAnimator: FragmentTransactionAnimator) {

    fun create(
        activity: FragmentActivity,
        containerId: Int,
        fragmentManager: FragmentManager = activity.supportFragmentManager,
    ): AppNavigator =
        object : AppNavigator(activity, containerId, fragmentManager) {

            override fun setupFragmentTransaction(
                screen: FragmentScreen,
                fragmentTransaction: FragmentTransaction,
                currentFragment: Fragment?,
                nextFragment: Fragment,
            ) {
                fragmentTransactionAnimator.setAnimation(nextFragment, fragmentTransaction)
            }
        }
}