package com.shellwoo.kinoguru.feature.language.presentation

import com.shellwoo.kinoguru.core.test.unit.InstantTaskExecutorExtension
import com.shellwoo.kinoguru.core.test.unit.TestCoroutineExtension
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.domain.usecase.GetAllLanguagesUseCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class, InstantTaskExecutorExtension::class, TestCoroutineExtension::class)
class LanguageViewModelTest {

    private val getAllLanguagesUseCase: GetAllLanguagesUseCase = mock()
    private val router: LanguageRouter = mock()

    private val viewModel = LanguageViewModel(getAllLanguagesUseCase, router)

    @Test
    fun `init EXPECT languages`() {
        val expected = listOf(Language.ENGLISH, Language.RUSSIAN)
        whenever(getAllLanguagesUseCase()).thenReturn(expected)

        val actual = viewModel.languages.value

        assertEquals(expected, actual)
    }

    @Test
    fun `close EXPECT router close`() {
        viewModel.close()

        verify(router).close()
    }
}