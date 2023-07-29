package com.shellwoo.kinoguru.shared.language.data.repository

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.shellwoo.kinoguru.shared.language.data.converter.LanguageTagConverter
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
private class LanguageRepositoryImplTest {

    private val languageTagConverter: LanguageTagConverter = mock()

    private val repository = LanguageRepositoryImpl(languageTagConverter)

    @Test
    fun `get all EXPECT all languages`() {
        val expected = listOf(Language.ENGLISH, Language.RUSSIAN)

        val actual = repository.getAll()

        assertEquals(expected, actual)
    }

    @Test
    fun `get current EXPECT language`() {
        Mockito.mockStatic(AppCompatDelegate::class.java).use { appCompatDelegateMock ->
            val expected = Language.ENGLISH
            val tag = "en"
            val localeListCompat: LocaleListCompat = mock {
                on { toLanguageTags() } doReturn tag
            }
            appCompatDelegateMock.`when`<LocaleListCompat> { AppCompatDelegate.getApplicationLocales() }.thenReturn(localeListCompat)
            whenever(languageTagConverter.fromTag(tag)).thenReturn(expected)

            val actual = repository.getCurrent()

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `set current EXPECT app compat delegate set application locales`() {
        val language = Language.ENGLISH
        val tag = "en"
        val localeListCompat: LocaleListCompat = mock()
        val localeListCompatStaticMock = Mockito.mockStatic(LocaleListCompat::class.java)
        val appCompatDelegateStaticMock = Mockito.mockStatic(AppCompatDelegate::class.java)
        localeListCompatStaticMock.`when`<LocaleListCompat> { LocaleListCompat.forLanguageTags(tag) }.thenReturn(localeListCompat)
        whenever(languageTagConverter.toTag(language)).thenReturn(tag)

        repository.setCurrent(language)

        appCompatDelegateStaticMock.verify { AppCompatDelegate.setApplicationLocales(localeListCompat) }
    }
}