package com.libraryofthings.data.base

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class BaseDataSourceTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @Test
    fun `safeApiCall should return success when api call is successful`() = runTest(testDispatcher) {
        // Given
        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel("\"Success\""),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val httpClient = HttpClient(mockEngine)
        val dataSource = object : BaseDataSource(testDispatcher) {}

        // When
        val result = dataSource.safeApiCall<String> { httpClient.get("http://localhost") }

        // Then
        assertTrue(result.isSuccess)
        assertEquals("Success", result.getOrNull())
    }

    @Test
    fun `safeApiCall should return failure when api call fails`() = runTest(testDispatcher) {
        // Given
        val mockEngine = MockEngine { respond(status = HttpStatusCode.InternalServerError, content = "Error") }
        val httpClient = HttpClient(mockEngine)
        val dataSource = object : BaseDataSource(testDispatcher) {}

        // When
        val result = dataSource.safeApiCall<String> { httpClient.get("http://localhost") }

        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `safeApiCall should return failure when api call throws an exception`() = runTest(testDispatcher) {
        // Given
        val mockEngine = MockEngine { throw RuntimeException("Network Error") }
        val httpClient = HttpClient(mockEngine)
        val dataSource = object : BaseDataSource(testDispatcher) {}

        // When
        val result = dataSource.safeApiCall<String> { httpClient.get("http://localhost") }

        // Then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is RuntimeException)
    }
}