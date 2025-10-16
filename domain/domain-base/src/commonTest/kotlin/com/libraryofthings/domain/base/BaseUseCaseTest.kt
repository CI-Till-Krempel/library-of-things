package com.libraryofthings.domain.base

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class BaseUseCaseTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @Test
    fun `invoke should return success when execute runs without error`() = runTest(testDispatcher) {
        // Given
        val successUseCase = object : BaseUseCase<String, String>(testDispatcher) {
            override suspend fun execute(params: String): String = "Success: $params"
        }

        // When
        val result = successUseCase("Test")

        // Then
        assertTrue(result.isSuccess)
        assertEquals("Success: Test", result.getOrNull())
    }

    @Test
    fun `invoke should return failure when execute throws an exception`() = runTest(testDispatcher) {
        // Given
        val failureUseCase = object : BaseUseCase<String, String>(testDispatcher) {
            override suspend fun execute(params: String): String = throw RuntimeException("Failure")
        }

        // When
        val result = failureUseCase("Test")

        // Then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is RuntimeException)
        assertEquals("Failure", result.exceptionOrNull()?.message)
    }
}
