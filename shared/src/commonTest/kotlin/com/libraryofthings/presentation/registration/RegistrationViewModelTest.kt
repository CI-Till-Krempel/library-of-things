package com.libraryofthings.presentation.registration

import com.libraryofthings.domain.model.User
import com.libraryofthings.domain.usecase.user.RegisterUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class RegistrationViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `registerUser success`() = runTest {
        val useCase = RegisterUserUseCase(object : com.libraryofthings.domain.repository.user.UserRegistrationRepository {
            override suspend fun registerUser(email: String, password: String): Result<User> {
                return Result.success(User(id = "1", email = email))
            }
        })
        val viewModel = RegistrationViewModel(useCase)

        viewModel.registerUser("test@example.com", "password", "password")
        testDispatcher.scheduler.advanceUntilIdle()


        assertEquals(RegistrationState.Success, viewModel.registrationState.value)
    }

    @Test
    fun `registerUser password mismatch`() = runTest {
        val useCase = RegisterUserUseCase(object : com.libraryofthings.domain.repository.user.UserRegistrationRepository {
            override suspend fun registerUser(email: String, password: String): Result<User> {
                return Result.success(User(id = "1", email = email))
            }
        })
        val viewModel = RegistrationViewModel(useCase)

        viewModel.registerUser("test@example.com", "password", "wrong_password")

        assertEquals(RegistrationState.Error("Passwords do not match"), viewModel.registrationState.value)
    }

    @Test
    fun `registerUser failure`() = runTest {
        val exception = Exception("Registration failed")
        val useCase = RegisterUserUseCase(object : com.libraryofthings.domain.repository.user.UserRegistrationRepository {
            override suspend fun registerUser(email: String, password: String): Result<User> {
                return Result.failure(exception)
            }
        })
        val viewModel = RegistrationViewModel(useCase)

        viewModel.registerUser("test@example.com", "password", "password")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(RegistrationState.Error("Registration failed"), viewModel.registrationState.value)
    }
}
