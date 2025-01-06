package com.fabianospdev.mindflow.features.settings.presentation.ui.settings.states

/**
 * Unauthorized state: Represents an authentication failure due to invalid credentials or lack of permission
 * (e.g., HTTP status 401). This state can be used to specifically handle unauthorized access errors.
 */
data class SettingsUnauthorized(val message: String) : SettingsState()