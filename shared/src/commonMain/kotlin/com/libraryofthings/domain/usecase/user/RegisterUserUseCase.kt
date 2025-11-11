package com.libraryofthings.domain.usecase.user

import com.libraryofthings.domain.model.User
import com.libraryofthings.domain.repository.user.UserRegistrationRepository

class RegisterUserUseCase(
    private val userRegistrationRepository: UserRegistrationRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        if (email.isBlank() || password.isBlank()) {
            return Result.failure(IllegalArgumentException("Email and password cannot be blank"))
        }
        // Add more validation logic here if needed, e.g., for email format, password strength, etc.
        return userRegistrationRepository.registerUser(email, password)
    }
}
