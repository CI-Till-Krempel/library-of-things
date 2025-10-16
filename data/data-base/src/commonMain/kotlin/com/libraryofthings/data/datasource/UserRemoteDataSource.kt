package com.libraryofthings.data.datasource

import com.libraryofthings.domain.model.User

interface UserRemoteDataSource {
    suspend fun register(email: String, password: String): Result<User>
    suspend fun login(email: String, password: String): Result<User>
}