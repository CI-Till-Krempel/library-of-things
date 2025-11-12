package com.libraryofthings.data.user

import com.libraryofthings.domain.model.User
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FirebaseUserRegistrationDataSourceTest {

    // This test requires a running Firebase emulator or a real Firebase project.
    // Make sure to configure the Firebase project in the build scripts.
    @Test
    fun `register user with valid email and password`() = runBlocking {
        val dataSource = FirebaseUserRegistrationDataSource()
        val email = "test@example.com"
        val password = "password"

        // Clean up before test
        deleteUser(email, password)

        val result = dataSource.register(email, password)

        assertEquals(true, result.isSuccess)
        val user = result.getOrNull()
        assertEquals(email, user?.email)

        // Clean up after test
        deleteUser(email, password)
    }

    @Test
    fun `register user with existing email`() = runBlocking {
        val dataSource = FirebaseUserRegistrationDataSource()
        val email = "test@example.com"
        val password = "password"

        // Clean up before test
        deleteUser(email, password)

        // First registration should succeed
        dataSource.register(email, password)

        // Second registration should fail
        val result = dataSource.register(email, password)
        assertEquals(true, result.isFailure)

        // Clean up after test
        deleteUser(email, password)
    }

    private suspend fun deleteUser(email: String, password: String) {
        try {
            val user = Firebase.auth.signInWithEmailAndPassword(email, password).user
            user?.delete()
        } catch (e: Exception) {
            // Ignore if user does not exist
        }
    }
}
