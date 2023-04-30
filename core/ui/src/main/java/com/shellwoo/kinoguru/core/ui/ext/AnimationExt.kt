package com.shellwoo.kinoguru.core.ui.ext

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View

fun View.animateOfIncreaseAndDecrease(
    minRelativeSize: Float,
    maxRelativeSize: Float,
    cycleDuration: Long,
) {
    ObjectAnimator.ofFloat(this, "scaleX", minRelativeSize, maxRelativeSize)
        .setDuration(cycleDuration / 2)
        .apply {
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
        }
        .start()

    ObjectAnimator.ofFloat(this, "scaleY", minRelativeSize, maxRelativeSize)
        .setDuration(cycleDuration / 2)
        .apply {
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
        }
        .start()
}