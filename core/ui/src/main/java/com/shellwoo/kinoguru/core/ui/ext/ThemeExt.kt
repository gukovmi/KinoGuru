package com.shellwoo.kinoguru.core.ui.ext

import android.content.Context
import android.content.res.TypedArray
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

@ColorInt
fun getThemeColor(context: Context, @AttrRes attr: Int, @ColorInt defaultColor: Int = 0): Int {
    val styledAttrs = getThemeStyledAttributes(context, attr)
    val color = styledAttrs.getColor(0, defaultColor)
    styledAttrs.recycle()
    return color
}

private fun getThemeStyledAttributes(context: Context, @AttrRes attr: Int): TypedArray {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(attr, typedValue, true)
    return context.obtainStyledAttributes(typedValue.resourceId, intArrayOf(attr))
}