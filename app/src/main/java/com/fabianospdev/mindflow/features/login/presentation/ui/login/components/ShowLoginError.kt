package com.fabianospdev.mindflow.features.login.presentation.ui.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fabianospdev.mindflow.core.helpers.exceptions.CommonError
import com.fabianospdev.mindflow.features.login.presentation.ui.login.LoginError
import com.fabianospdev.mindflow.features.login.presentation.ui.login.states.LoginState

@Composable
fun ShowLoginError(state: LoginState) {
    val errorMessage = when ((state as LoginState.LoginError).error) {
        LoginError.UserNotFound.toString() -> LoginError.UserNotFound.message
        LoginError.LoginFailed.toString() -> LoginError.LoginFailed.message
        else -> CommonError.UnknownError.message
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("ERROR: $errorMessage")
    }
}