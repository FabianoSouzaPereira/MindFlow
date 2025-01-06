package com.fabianospdev.mindflow.features.settings.presentation.ui.settings.states

/**
 * ValidationError state: Indicates a failure due to local validation issues, such as incorrect input format
 * (e.g., invalid email or password length) before attempting to contact the server.
 */
data class SettingsValidationError(val message: String) : SettingsState()