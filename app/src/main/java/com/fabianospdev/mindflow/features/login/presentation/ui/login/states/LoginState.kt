package com.fabianospdev.mindflow.features.login.presentation.ui.login.states

import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity

// LoginStates.kt
sealed class LoginState {
    object LoginLoading : LoginState()
    object LoginIdle : LoginState()
    data class LoginSuccess(val response: LoginResponseEntity) : LoginState()
    data class LoginError(val error: String) : LoginState()
    data class LoginNoConnection(val errorMessage: String) : LoginState()
    data class LoginValidationError(val message: String) : LoginState()
    data class LoginTimeoutError(val message: String) : LoginState()
    data class LoginUnauthorized(val message: String) : LoginState()
    data class LoginUnknown(val message: String) : LoginState()
}
