package com.shellwoo.kinoguru.core.ui.ext

import android.os.Build
import android.view.View
import android.view.WindowInsets

const val POINT_ARGS_NUMBER = 2
const val X_POSITION = 0
const val Y_POSITION = 1

fun View.getTopOnWindow(): Int {
    val point = IntArray(POINT_ARGS_NUMBER)
    getLocationInWindow(point)
    return point[Y_POSITION]
}

fun View.getLeftOnWindow(): Int {
    val point = IntArray(POINT_ARGS_NUMBER)
    getLocationInWindow(point)
    return point[X_POSITION]
}

fun View.getInsetTop(): Int =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        rootWindowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars()).top
    } else {
        rootWindowInsets.systemWindowInsetTop
    }

fun View.onPause(action: () -> Unit) {
    viewTreeObserver.addOnWindowFocusChangeListener { hasFocus ->
        if (!hasFocus) {
            action()
        }
    }
}