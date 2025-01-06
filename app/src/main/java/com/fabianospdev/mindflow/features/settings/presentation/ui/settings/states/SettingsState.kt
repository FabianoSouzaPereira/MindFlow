package com.fabianospdev.mindflow.features.settings.presentation.ui.settings.states

import com.fabianospdev.mindflow.features.settings.domain.entities.SettingsResponseEntity

// SettingsStates.kt
sealed class SettingsState {
    object SettingsLoading : SettingsState()
    object SettingsIdle : SettingsState()
    data class SettingsSuccess(val response: SettingsResponseEntity) : SettingsState()
    data class SettingsError(val error: String) : SettingsState()
    data class SettingsNoConnection(val errorMessage: String) : SettingsState()
    data class SettingsValidationError(val message: String) : SettingsState()
    data class SettingsTimeoutError(val message: String) : SettingsState()
    data class SettingsUnauthorized(val message: String) : SettingsState()
    data class SettingsUnknown(val message: String) : SettingsState()
}
