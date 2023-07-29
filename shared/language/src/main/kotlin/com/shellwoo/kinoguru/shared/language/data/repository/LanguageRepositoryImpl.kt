package com.shellwoo.kinoguru.shared.language.data.repository

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.shellwoo.kinoguru.shared.language.data.converter.LanguageTagConverter
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.domain.repository.LanguageRepository
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(
    private val languageTagConverter: LanguageTagConverter
) : LanguageRepository {

    override fun getAll(): List<Language> =
        Language.values().toList()

    override fun getCurrent(): Language {
        val localeListCompat = AppCompatDelegate.getApplicationLocales()
        val tag = localeListCompat.toLanguageTags()
        return languageTagConverter.fromTag(tag)
    }

    override fun setCurrent(language: Language) {
        val languageTag = languageTagConverter.toTag(language)
        val localeListCompat = LocaleListCompat.forLanguageTags(languageTag)
        AppCompatDelegate.setApplicationLocales(localeListCompat)
    }
}