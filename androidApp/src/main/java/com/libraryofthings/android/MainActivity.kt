package com.libraryofthings.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.libraryofthings.presentation.registration.RegistrationScreen
import com.libraryofthings.presentation.registration.RegistrationViewModel
import com.libraryofthings.data.user.FirebaseUserRegistrationDataSource
import com.libraryofthings.domain.repository.user.UserRegistrationRepositoryImpl
import com.libraryofthings.domain.usecase.user.RegisterUserUseCase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // In a real application, you would use a dependency injection framework
            // to provide the ViewModel. For now, we'll create it manually.
            val userRegistrationDataSource = FirebaseUserRegistrationDataSource()
            val userRegistrationRepository = UserRegistrationRepositoryImpl(userRegistrationDataSource)
            val registerUserUseCase = RegisterUserUseCase(userRegistrationRepository)
            val registrationViewModel = RegistrationViewModel(registerUserUseCase)
            RegistrationScreen(registrationViewModel)
        }
    }
}
