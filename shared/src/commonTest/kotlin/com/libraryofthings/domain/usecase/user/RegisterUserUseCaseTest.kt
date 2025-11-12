package com.libraryofthings.domain.usecase.user

import com.libraryofthings.domain.model.User
import com.libraryofthings.domain.repository.user.UserRegistrationRepository
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class RegisterUserUseCaseTest {

    @Test
    fun `invoke returns success when repository succeeds`() = runBlocking {
        val user = User(id = "1", email = "test@example.com")
        val repository = object : UserRegistrationRepository {
            override suspend fun registerUser(email: String, password: String): Result<User> {
                return Result.success(user)
            }
        }
        val useCase = RegisterUserUseCase(repository)

        val result = useCase("test@example.com", "password")

        assertEquals(true, result.isSuccess)
        assertEquals(user, result.getOrNull())
    }

    @Test
    fun `invoke returns failure when repository fails`() = runBlocking {
        val exception = Exception("Registration failed")
        val repository = object : UserRegistrationRepository {
            override suspend fun registerUser(email: String, password: String): Result<User> {
                return Result.failure(exception)
            }
        }
        val useCase = RegisterUserUseCase(repository)

        val result = useCase("test@example.com", "password")

        assertEquals(true, result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `invoke returns failure when email is blank`() = runBlocking {
        val repository = object : UserRegistrationRepository {
            override suspend fun registerUser(email: String, password: String): Result<User> {
                return Result.success(User(id = "1", email = email))
            }
        }
        val useCase = RegisterUserUseCase(repository)

        val result = useCase("", "password")

        assertEquals(true, result.isFailure)
        assertEquals("Email and password cannot be blank", result.exceptionOrNull()?.message)
    }

    @Test
    fun `invoke returns failure when password is blank`() = runBlocking {
        val repository = object : UserRegistrationRepository {
            override suspend fun registerUser(email: String, password: String): Result<User> {
                return Result.success(User(id = "1", email = email))
            }
        }
        val useCase = RegisterUserUseCase(repository)

        val result = useCase("test@example.com", "")

        assertEquals(true, result.isFailure)
        assertEquals("Email and password cannot be blank", result.exceptionOrNull()?.message)
    }
}
