package com.fabianospdev.mindflow.features.settings.presentation.ui.settings.states

/**
 * TimeoutError state: Represents a timeout scenario where the server takes too long to respond to the login request,
 * signaling the need to retry or alert the user about the delay.
 */
data class SettingsTimeoutError(val message: String) : SettingsState()