package com.fabianospdev.mindflow.features.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.fabianospdev.mindflow.features.login.domain.usecases.LoginRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginRemoteUseCase
) : ViewModel() {

    var username = mutableStateOf("")
    var password = mutableStateOf("")

    /** Field validation (calculated in a derived way) **/
    var isUserNameEmpty = derivedStateOf { username.value.isEmpty() }
    var isPasswordEmpty = derivedStateOf { password.value.isEmpty() }
    var isFormValid = derivedStateOf { username.value.isNotEmpty() && password.value.isNotEmpty() }


    fun login() {
        // Lógica do login
    }
}
