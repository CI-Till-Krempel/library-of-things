package com.libraryofthings.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.libraryofthings.data.user.FirebaseUserRegistrationDataSource
import com.libraryofthings.domain.repository.user.UserRegistrationRepositoryImpl
import com.libraryofthings.domain.usecase.user.RegisterUserUseCase
import com.libraryofthings.presentation.registration.RegistrationScreen
import com.libraryofthings.presentation.registration.RegistrationViewModel

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Library of Things") {
        // In a real application, you would use a dependency injection framework
        // to provide the ViewModel. For now, we'll create it manually.
        val userRegistrationDataSource = FirebaseUserRegistrationDataSource()
        val userRegistrationRepository = UserRegistrationRepositoryImpl(userRegistrationDataSource)
        val registerUserUseCase = RegisterUserUseCase(userRegistrationRepository)
        val registrationViewModel = RegistrationViewModel(registerUserUseCase)
        RegistrationScreen(registrationViewModel)
    }
}
