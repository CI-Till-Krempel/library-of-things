package com.libraryofthings.presentation.registration

import com.libraryofthings.domain.usecase.user.RegisterUserUseCase
import com.libraryofthings.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registerUserUseCase: RegisterUserUseCase
) : BaseViewModel() {

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val registrationState: StateFlow<RegistrationState> = _registrationState

    fun registerUser(email: String, password: String, passwordConfirmation: String) {
        if (password != passwordConfirmation) {
            _registrationState.value = RegistrationState.Error("Passwords do not match")
            return
        }

        viewModelScope.launch {
            _registrationState.value = RegistrationState.Loading
            val result = registerUserUseCase(email, password)
            result.onSuccess {
                _registrationState.value = RegistrationState.Success
            }.onFailure {
                _registrationState.value = RegistrationState.Error(it.message ?: "An unknown error occurred")
            }
        }
    }
}

sealed class RegistrationState {
    object Idle : RegistrationState()
    object Loading : RegistrationState()
    object Success : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}
