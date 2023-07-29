package com.shellwoo.kinoguru.feature.profile.domain.scenario

import com.shellwoo.kinoguru.feature.profile.domain.entity.Profile
import com.shellwoo.kinoguru.shared.language.domain.entity.Language
import com.shellwoo.kinoguru.shared.language.domain.usecase.GetCurrentLanguageUseCase
import com.shellwoo.kinoguru.shared.user.domain.entity.User
import com.shellwoo.kinoguru.shared.user.domain.usecase.GetCurrentUserUseCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class GetProfileScenarioTest {

    private val getCurrentUserUseCase: GetCurrentUserUseCase = mock()
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase = mock()

    private val scenario = GetProfileScenario(getCurrentUserUseCase, getCurrentLanguageUseCase)

    @Test
    fun `invoke, current user is not null EXPECT profile`() {
        val user = User(
            name = "Max",
            email = "max@gmail.com",
            photoUrl = "google.com/images",
        )
        val expected = Profile(
            name = "Max",
            email = "max@gmail.com",
            photoUrl = "google.com/images",
            language = Language.ENGLISH,
        )
        whenever(getCurrentUserUseCase()).thenReturn(user)
        whenever(getCurrentLanguageUseCase()).thenReturn(Language.ENGLISH)

        val actual = scenario()

        assertEquals(expected, actual)
    }

    @Test
    fun `invoke, current user is null EXPECT null`() {
        whenever(getCurrentUserUseCase()).thenReturn(null)

        assertNull(scenario())
    }
}