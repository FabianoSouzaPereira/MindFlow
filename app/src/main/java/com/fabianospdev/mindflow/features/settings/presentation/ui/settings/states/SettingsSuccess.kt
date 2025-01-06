package com.fabianospdev.mindflow.features.settings.presentation.ui.settings.states

import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity

/**
 * Success state: Represents a successful login response. Contains the result of the login attempt, which includes
 * the response from the server (usually a success status and user data).
 */
data class SettingsSuccess(val response: LoginResponseEntity) : SettingsState() {
    fun isTokenRelated(): Boolean {
        return response.token.isNotEmpty()
    }
}