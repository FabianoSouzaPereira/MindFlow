package com.fabianospdev.mindflow.core.helpers

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppConfig(initialState: Boolean) {
    private val _isUsingFirebaseStateFlow = MutableStateFlow(initialState)
    val isUsingFirebase: StateFlow<Boolean> get() = _isUsingFirebaseStateFlow

    fun setUsingFirebase(isUsing: Boolean) {
        _isUsingFirebaseStateFlow.value = isUsing
    }
}