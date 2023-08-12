package com.shellwoo.kinoguru.shared.theme.ui

import com.shellwoo.kinoguru.core.ui.FragmentResultContract
import com.shellwoo.kinoguru.shared.theme.domain.entity.Theme

object ThemeResultContract : FragmentResultContract<Theme> {

    override val key: String = "ThemeResult"
}