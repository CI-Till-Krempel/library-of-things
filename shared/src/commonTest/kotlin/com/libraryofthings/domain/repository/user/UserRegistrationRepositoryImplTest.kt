package com.libraryofthings.domain.repository.user

import com.libraryofthings.data.user.UserRegistrationDataSource
import com.libraryofthings.domain.model.User
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class UserRegistrationRepositoryImplTest {

    @Test
    fun `registerUser returns success when data source succeeds`() = runBlocking {
        val user = User(id = "1", email = "test@example.com")
        val dataSource = object : UserRegistrationDataSource {
            override suspend fun register(email: String, password: String): Result<User> {
                return Result.success(user)
            }
        }
        val repository = UserRegistrationRepositoryImpl(dataSource)

        val result = repository.registerUser("test@example.com", "password")

        assertEquals(true, result.isSuccess)
        assertEquals(user, result.getOrNull())
    }

    @Test
    fun `registerUser returns failure when data source fails`() = runBlocking {
        val exception = Exception("Registration failed")
        val dataSource = object : UserRegistrationDataSource {
            override suspend fun register(email: String, password: String): Result<User> {
                return Result.failure(exception)
            }
        }
        val repository = UserRegistrationRepositoryImpl(dataSource)

        val result = repository.registerUser("test@example.com", "password")

        assertEquals(true, result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
