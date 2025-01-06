package com.fabianospdev.mindflow.features.login.presentation.ui.login.states

/**
 * Error state: Represents a general error state that occurs if the login attempt fails. The error message can
 * be used to describe the nature of the failure (e.g., incorrect credentials).
 */
data class LoginError(val error: String) : LoginState() {
    fun isNetworkRelated(): Boolean {
        return error.contains("network", ignoreCase = true)
    }
}