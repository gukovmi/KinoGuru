package com.shellwoo.kinoguru.shared.language.data.converter

import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

private class LanguageTagConverterTest {

    private val converter = LanguageTagConverter()

    @Test
    fun `to tag EXPECT tag`() {
        val language = Language.ENGLISH
        val expected = "en"

        val actual = converter.toTag(language)

        assertEquals(expected, actual)
    }

    @Test
    fun `from tag EXPECT language`() {
        val tag = "en"
        val expected = Language.ENGLISH

        val actual = converter.fromTag(tag)

        assertEquals(expected, actual)
    }
}