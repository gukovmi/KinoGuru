package com.shellwoo.kinoguru.core.ui.component

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.shellwoo.kinoguru.core.ui.R
import kotlin.reflect.KClass

class FragmentTransactionAnimator(
    private val rootFragments: Set<KClass<out Fragment>>,
) {

    fun setAnimation(nextFragment: Fragment, fragmentTransaction: FragmentTransaction) {
        when (nextFragment::class) {
            in rootFragments -> setSlideFromBottomAnimation(fragmentTransaction)
        }
    }

    private fun setSlideFromBottomAnimation(fragmentTransaction: FragmentTransaction) {
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in_up_with_show,
            0,
            0,
            R.anim.slide_out_down_with_fade,
        )
    }
}