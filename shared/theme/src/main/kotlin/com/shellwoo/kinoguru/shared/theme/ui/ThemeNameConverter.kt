package com.shellwoo.kinoguru.shared.theme.ui

import android.content.Context
import com.shellwoo.kinoguru.shared.theme.R
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme

class ThemeNameConverter(private val context: Context) {

    fun toName(theme: Theme): String =
        when (theme) {
            Theme.LIGHT -> context.getString(R.string.theme_light)
            Theme.DARK -> context.getString(R.string.theme_dark)
            Theme.AUTO_TIME -> context.getString(R.string.theme_auto_time)
            Theme.FOLLOW_SYSTEM -> context.getString(R.string.theme_follow_system)
            Theme.AUTO_BATTERY -> context.getString(R.string.theme_auto_battery)
            Theme.UNSPECIFIED -> context.getString(R.string.theme_unspecified)
        }
}