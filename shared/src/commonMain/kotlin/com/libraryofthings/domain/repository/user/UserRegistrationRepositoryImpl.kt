package com.libraryofthings.domain.repository.user

import com.libraryofthings.data.user.UserRegistrationDataSource
import com.libraryofthings.domain.model.User

class UserRegistrationRepositoryImpl(
    private val userRegistrationDataSource: UserRegistrationDataSource
) : UserRegistrationRepository {
    override suspend fun registerUser(email: String, password: String): Result<User> {
        return userRegistrationDataSource.register(email, password)
    }
}
