package com.shellwoo.kinoguru.shared.language.ui

import android.content.Context
import com.shellwoo.kinoguru.shared.language.R
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import javax.inject.Inject

class LanguageNameConverter @Inject constructor(private val context: Context) {

    fun toName(language: Language): String =
        when (language) {
            Language.ENGLISH -> context.getString(R.string.language_english)
            Language.RUSSIAN -> context.getString(R.string.language_russian)
        }
}