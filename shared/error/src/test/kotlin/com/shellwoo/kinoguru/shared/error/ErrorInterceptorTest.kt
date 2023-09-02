package com.shellwoo.kinoguru.shared.error

import com.shellwoo.kinoguru.shared.error.data.converter.ConnectExceptionConverter
import com.shellwoo.kinoguru.shared.error.data.converter.HttpCodeExceptionConverter
import com.shellwoo.kinoguru.shared.error.domain.exception.ConnectException
import com.shellwoo.kinoguru.shared.error.domain.exception.DomainException
import com.shellwoo.kinoguru.shared.error.domain.exception.NotFoundException
import com.shellwoo.kinoguru.shared.error.domain.exception.ServiceConnectException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_OK

@ExtendWith(MockitoExtension::class)
class ErrorInterceptorTest {

    private val httpCodeExceptionConverter: HttpCodeExceptionConverter = mock()
    private val connectExceptionConverter: ConnectExceptionConverter = mock()

    private val interceptor = ErrorInterceptor(httpCodeExceptionConverter, connectExceptionConverter)

    @Test
    fun `intercept without error, ok http code EXPECT response`() {
        val request: Request = mock()
        val expected: Response = mock {
            on { it.code } doReturn HTTP_OK
        }
        val chain: Interceptor.Chain = mock {
            on { it.request() } doReturn request
            on { it.proceed(request) } doReturn expected
        }

        val actual = interceptor.intercept(chain)

        assertEquals(actual, expected)
    }

    @Test
    fun `intercept with error (not exception) EXPECT error`() {
        val request: Request = mock()
        val chain: Interceptor.Chain = mock {
            on { it.request() } doReturn request
            on { it.proceed(request) } doThrow Error()
        }

        assertThrows<Error> { interceptor.intercept(chain) }
    }

    @Test
    fun `intercept with error (exception) EXPECT connect exception`() {
        val request: Request = mock()
        val requestException = RuntimeException()
        val chain: Interceptor.Chain = mock {
            on { it.request() } doReturn request
            on { it.proceed(request) } doThrow requestException
        }
        val connectException = ServiceConnectException("")
        whenever(connectExceptionConverter.convert(requestException)).thenReturn(connectException)

        assertThrows<ConnectException> { interceptor.intercept(chain) }
    }

    @Test
    fun `intercept without error, http code is not ok EXPECT domain exception`() {
        val request: Request = mock()
        val httpNotFoundCode = HTTP_NOT_FOUND
        val response: Response = mock {
            on { it.code } doReturn httpNotFoundCode
        }
        val chain: Interceptor.Chain = mock {
            on { it.request() } doReturn request
            on { it.proceed(request) } doReturn response
        }
        val domainException = NotFoundException("")
        whenever(httpCodeExceptionConverter.convert(httpNotFoundCode)).thenReturn(domainException)

        assertThrows<DomainException> { interceptor.intercept(chain) }
    }
}