package com.shellwoo.kinoguru.core.ui.component

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlin.reflect.KClass
import com.shellwoo.kinoguru.design.resource.R as designResourceR

class FragmentTransactionAnimator(
    private val rootFragments: Set<KClass<out Fragment>>,
    private val flowFragments: Set<KClass<out Fragment>>,
) {

    fun setAnimation(nextFragment: Fragment, fragmentTransaction: FragmentTransaction) {
        when (nextFragment::class) {
            in rootFragments -> setSlideFromBottomAnimation(fragmentTransaction)
            in flowFragments -> setSlideFromRightAnimation(fragmentTransaction)
        }
    }

    private fun setSlideFromBottomAnimation(fragmentTransaction: FragmentTransaction) {
        fragmentTransaction.setCustomAnimations(
            designResourceR.anim.slide_in_up_with_show,
            0,
            0,
            designResourceR.anim.slide_out_down_with_fade,
        )
    }

    private fun setSlideFromRightAnimation(fragmentTransaction: FragmentTransaction) {
        fragmentTransaction.setCustomAnimations(
            designResourceR.anim.slide_in_left_with_show,
            0,
            0,
            designResourceR.anim.slide_out_right_with_show,
        )
    }
}