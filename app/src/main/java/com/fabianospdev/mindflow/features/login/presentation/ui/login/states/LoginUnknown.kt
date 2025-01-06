package com.fabianospdev.mindflow.features.login.presentation.ui.login.states

data class LoginUnknown(val message: String): LoginState() {

    fun isLoginUnknown() : Boolean {
        return message.isNotEmpty()
    }

    override fun toString(): String {
        return "Error unknown occurred with message: $message"
    }
}