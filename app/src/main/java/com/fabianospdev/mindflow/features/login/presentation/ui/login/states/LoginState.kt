package com.fabianospdev.mindflow.features.login.presentation.ui.login.states

import com.fabianospdev.mindflow.features.login.domain.entities.LoginResponseEntity

sealed class LoginState {
    object LoginLoading : LoginState()

    object LoginIdle : LoginState()

    data class LoginSuccess(val response: LoginResponseEntity) : LoginState() {
        fun isTokenRelated(): Boolean {
            return response.token.isNotEmpty()
        }
    }

    data class LoginError(val error: String) : LoginState()

    data class LoginNoConnection(val errorMessage: String) : LoginState()

    data class LoginValidationError(val message: String) : LoginState()

    data class LoginTimeoutError(val message: String) : LoginState()

    data class LoginUnauthorized(val message: String) : LoginState()

    data class LoginUnknown(val message: String) : LoginState() {
        fun isLoginUnknown(): Boolean {
            return message.isNotEmpty()
        }

        override fun toString(): String {
            return "Error unknown occurred with message: $message"
        }
    }
}
