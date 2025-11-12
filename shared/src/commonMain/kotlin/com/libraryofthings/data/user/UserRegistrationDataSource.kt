package com.libraryofthings.data.user

import com.libraryofthings.domain.model.User

interface UserRegistrationDataSource {
    suspend fun register(email: String, password: String): Result<User>
}
