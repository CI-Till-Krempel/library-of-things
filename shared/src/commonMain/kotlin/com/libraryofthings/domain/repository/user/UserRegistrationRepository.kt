package com.libraryofthings.domain.repository.user

import com.libraryofthings.domain.model.User

interface UserRegistrationRepository {
    suspend fun registerUser(email: String, password: String): Result<User>
}
