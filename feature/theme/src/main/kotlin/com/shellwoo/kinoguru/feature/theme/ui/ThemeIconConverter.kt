package com.shellwoo.kinoguru.feature.theme.ui

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.shellwoo.kinoguru.feature.theme.R
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme
import javax.inject.Inject

class ThemeIconConverter @Inject constructor(private val context: Context) {

    fun toIcon(theme: Theme): Drawable =
        when (theme) {
            Theme.LIGHT -> ContextCompat.getDrawable(context, R.drawable.light)
            Theme.DARK -> ContextCompat.getDrawable(context, R.drawable.dark)
            Theme.AUTO_TIME -> ContextCompat.getDrawable(context, R.drawable.time)
            Theme.FOLLOW_SYSTEM -> ContextCompat.getDrawable(context, R.drawable.settings)
            Theme.AUTO_BATTERY -> ContextCompat.getDrawable(context, R.drawable.battery)
            Theme.UNSPECIFIED -> throw IllegalArgumentException("Theme: $theme is not available")
        } ?: throw IllegalArgumentException("Icon not found")
}