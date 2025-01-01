package com.fabianospdev.mindflow.features.login.presentation.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.login.domain.usecases.LoginRemoteUseCase
import com.fabianospdev.mindflow.features.login.presentation.ui.login.LoginPresenterError
import com.fabianospdev.mindflow.features.login.presentation.ui.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginRemoteUseCase,
    private val retryController: RetryController,
    private val appConfig: AppConfig
) : ViewModel() {

    var username = mutableStateOf("")
    var password = mutableStateOf("")

    /** Field validation (calculated in a derived way) **/
    var isUserNameEmpty = derivedStateOf { username.value.isEmpty() }
    var isPasswordEmpty = derivedStateOf { password.value.isEmpty() }
    var isFormValid = derivedStateOf { username.value.isNotEmpty() && password.value.isNotEmpty() }

    private val _showRetryLimitReached = MutableStateFlow(false)
    val showRetryLimitReached: StateFlow<Boolean> get() = _showRetryLimitReached

    val isUsingFirebase: StateFlow<Boolean> = appConfig.isUsingFirebase

    fun toggleFirebaseUsage() {
        appConfig.setUsingFirebase(!appConfig.isUsingFirebase.value)
    }

    private val _state = MutableLiveData<LoginState>(LoginState.Idle)
    val state: LiveData<LoginState> get() = _state


    fun login(username: String, password: String){
        if (!retryController.isRetryEnabled.value) {
            _showRetryLimitReached.value = true
            return
        }
        _state.value = LoginState.Loading

        viewModelScope.launch {
            try {
                delay(2000) //todo remove it

                val response = useCase.login(username, password)
                if (response.token.isNotEmpty()) {
                    _state.value = LoginState.Success(response)
                    retryController.resetRetryCount()
                } else {
                    retryController.incrementRetryCount()
                    _state.value = LoginState.Error(LoginPresenterError.LoginFailed.message)
                }

            } catch (e: Exception){
                retryController.incrementRetryCount()
                _state.value = when (e){
                    is com.fabianospdev.mindflow.features.login.domain.exceptions.UserNotFoundException -> LoginState.Error(
                        LoginPresenterError.UserNotFound.message
                    )
                    is com.fabianospdev.mindflow.features.login.domain.exceptions.TimeoutException -> LoginState.TimeoutError(
                        LoginPresenterError.TimeoutError.message
                    )
                    is com.fabianospdev.mindflow.features.login.domain.exceptions.UnauthorizedException -> LoginState.Unauthorized(
                        LoginPresenterError.Unauthorized.message
                    )
                    is com.fabianospdev.mindflow.features.login.domain.exceptions.ValidationException -> LoginState.ValidationError(
                        LoginPresenterError.ValidationError.message
                    )
                    else -> LoginState.Error(LoginPresenterError.LoginFailed.message)
                }
            }
        }
    }

    fun resetRetryLimitNotification() {
        _showRetryLimitReached.value = false
        retryController.resetRetryCount()
    }

    fun resetState() {
        _state.value = LoginState.Idle
    }

    fun clearInputFields() {
        username.value = ""
        password.value = ""
    }
}