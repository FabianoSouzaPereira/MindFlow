package com.fabianospdev.mindflow.features.login.presentation.ui.login.states

/**
 * NoConnection state: Represents a failure caused by the absence of an internet connection. This state can be
 * used to notify the user that the app is unable to proceed due to network issues.
 */
data class LoginNoConnection(val errorMessage: String) : LoginState()