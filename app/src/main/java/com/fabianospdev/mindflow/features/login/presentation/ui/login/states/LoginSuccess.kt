package com.fabianospdev.mindflow.features.login.presentation.ui.login.states

import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity

/**
 * Success state: Represents a successful login response. Contains the result of the login attempt, which includes
 * the response from the server (usually a success status and user data).
 */
data class LoginSuccess(val response: LoginResponseEntity) : LoginState() {
    fun isTokenRelated(): Boolean {
        return response.token.isNotEmpty()
    }
}