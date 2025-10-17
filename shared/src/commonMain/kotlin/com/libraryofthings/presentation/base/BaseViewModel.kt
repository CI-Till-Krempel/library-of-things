package com.libraryofthings.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

expect abstract class BaseViewModel<S, E>() {
    abstract val state: StateFlow<S>
    val viewModelScope: CoroutineScope
    abstract fun onEvent(event: E)
}
