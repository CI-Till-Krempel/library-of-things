package com.libraryofthings.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

actual abstract class BaseViewModel<S, E> : ViewModel() {
    actual abstract val state: StateFlow<S>
    actual override val viewModelScope: CoroutineScope = this.viewModelScope
    actual abstract fun onEvent(event: E)
}
