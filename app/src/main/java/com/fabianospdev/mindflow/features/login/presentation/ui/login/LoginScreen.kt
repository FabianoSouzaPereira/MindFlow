package com.fabianospdev.mindflow.features.login.presentation.ui.login

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fabianospdev.mindflow.R
import com.fabianospdev.mindflow.features.login.presentation.ui.login.components.ShowCommonError
import com.fabianospdev.mindflow.features.login.presentation.ui.login.components.ShowLoginError
import com.fabianospdev.mindflow.features.login.presentation.ui.login.components.ShowLoginIdle
import com.fabianospdev.mindflow.features.login.presentation.ui.login.components.ShowLoginLoading
import com.fabianospdev.mindflow.features.login.presentation.ui.login.components.ShowLoginNoConnection
import com.fabianospdev.mindflow.features.login.presentation.ui.login.components.ShowLoginSuccess
import com.fabianospdev.mindflow.features.login.presentation.ui.login.components.ShowLoginTimeoutError
import com.fabianospdev.mindflow.features.login.presentation.ui.login.components.ShowLoginUnauthorized
import com.fabianospdev.mindflow.features.login.presentation.ui.login.components.ShowLoginValidationError
import com.fabianospdev.mindflow.features.login.presentation.ui.login.components.ShowPopup
import com.fabianospdev.mindflow.features.login.presentation.ui.login.states.LoginState
import com.fabianospdev.mindflow.features.login.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.state.observeAsState(LoginState.LoginIdle)
    val context = LocalContext.current

    /** Observing the ViewModel state **/
    val isUsingFirebase by viewModel.isUsingFirebase.collectAsState()
    val username by remember { viewModel.username }
    val password by remember { viewModel.password }

    /** Field validation (calculated in a derived way) **/
    val isUserNameEmpty by remember { viewModel.isUserNameEmpty }
    val isPasswordEmpty by remember { viewModel.isPasswordEmpty }
    val isFormValid by remember { viewModel.isFormValid }

    val showPassword = remember { mutableStateOf(value = false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val showRetryLimitReached by viewModel.showRetryLimitReached.collectAsState()
    var showPopup by remember { mutableStateOf(false) }

    val gradient = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary
        )
    )

    if (showRetryLimitReached && !showPopup) {
        showPopup = true
        viewModel.resetRetryLimitNotification()
    }

    if (showPopup) {
        ShowPopup(
            viewModel = viewModel,
            message = stringResource(R.string.attempt_limit_reached),
            onDismiss = { showPopup = false },
            imageResId = R.mipmap.ic_launcher_foreground
        )
        viewModel.resetState()
    }

    when (state) {
        is LoginState.LoginLoading -> {
            ShowLoginLoading()
        }

        is LoginState.LoginIdle -> {
            ShowLoginIdle(
                gradient,
                username,
                viewModel,
                focusRequester,
                password,
                keyboardController,
                showPassword,
                isPasswordEmpty,
                isFormValid,
                context
            )
        }

        is LoginState.LoginSuccess -> {
            ShowLoginSuccess(navController)
        }

        is LoginState.LoginError -> {
            ShowLoginError(state)
        }

        is LoginState.LoginNoConnection -> {
            ShowLoginNoConnection()
        }

        is LoginState.LoginTimeoutError -> {
            ShowLoginTimeoutError()
        }

        is LoginState.LoginUnauthorized -> {
            ShowLoginUnauthorized()
        }

        is LoginState.LoginValidationError -> {
            ShowLoginValidationError()
        }

        else -> {
            ShowCommonError()
        }
    }
}