package com.shellwoo.kinoguru.shared.language.ui

import com.shellwoo.kinoguru.core.ui.FragmentResultContract
import com.shellwoo.kinoguru.shared.language.domain.entity.Language

object LanguageResultContract : FragmentResultContract<Language> {

    override val key: String = "LanguageResult"
}