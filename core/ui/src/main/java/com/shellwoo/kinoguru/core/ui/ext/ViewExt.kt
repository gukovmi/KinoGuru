package com.shellwoo.kinoguru.core.ui.ext

import android.view.View

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