package com.libraryofthings.presentation.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.test.Test
import kotlin.test.assertNotNull

class BaseViewModelTest {

    // Dummy State and Event for testing
    data class TestState(val value: String = "")
    sealed class TestEvent {
        data object DummyEvent : TestEvent()
    }

    // A concrete implementation for testing purposes
    class TestViewModel : BaseViewModel<TestState, TestEvent>() {
        override val state: StateFlow<TestState> = MutableStateFlow(TestState())
        override fun onEvent(event: TestEvent) {}
    }

    @Test
    fun `BaseViewModel can be instantiated`() {
        val viewModel = TestViewModel()
        assertNotNull(viewModel)
        assertNotNull(viewModel.state)
        assertNotNull(viewModel.viewModelScope)
    }
}
