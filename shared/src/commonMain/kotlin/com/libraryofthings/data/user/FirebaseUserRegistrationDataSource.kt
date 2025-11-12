package com.libraryofthings.data.user

import com.libraryofthings.domain.model.User
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

class FirebaseUserRegistrationDataSource : UserRegistrationDataSource {
    override suspend fun register(email: String, password: String): Result<User> {
        return try {
            val firebaseAuth = Firebase.auth
            val userCredential = firebaseAuth.createUserWithEmailAndPassword(email, password)
            val firebaseUser = userCredential.user
            if (firebaseUser != null) {
                Result.success(User(id = firebaseUser.uid, email = firebaseUser.email!!))
            } else {
                Result.failure(Exception("User not found after registration"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
