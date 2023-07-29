package com.shellwoo.kinoguru.feature.language.ui

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.shellwoo.kinoguru.feature.language.R
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import javax.inject.Inject

class LanguageFlagConverter @Inject constructor(private val context: Context) {

    fun toFlag(language: Language): Drawable =
        when (language) {
            Language.ENGLISH -> ContextCompat.getDrawable(context, R.drawable.united_kingdom_flag)
            Language.RUSSIAN -> ContextCompat.getDrawable(context, R.drawable.russia_flag)
        } ?: throw IllegalArgumentException("Flag not found")
}