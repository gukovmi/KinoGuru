package com.shellwoo.kinoguru.shared.language.data.converter

import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import javax.inject.Inject

class LanguageTagConverter @Inject constructor() {

    private companion object {

        const val ENGLISH_TAG = "en"
        const val RUSSIAN_TAG = "ru"
    }

    fun toTag(language: Language): String =
        when (language) {
            Language.ENGLISH -> ENGLISH_TAG
            Language.RUSSIAN -> RUSSIAN_TAG
        }

    fun fromTag(tag: String): Language =
        when {
            tag.startsWith(RUSSIAN_TAG) -> Language.RUSSIAN
            else -> Language.ENGLISH
        }
}