package com.fabianospdev.mindflow.features.settings.presentation.ui.settings.states

/**
 * NoConnection state: Represents a failure caused by the absence of an internet connection. This state can be
 * used to notify the user that the app is unable to proceed due to network issues.
 */
data class SettingsNoConnection(val errorMessage: String) : SettingsState()