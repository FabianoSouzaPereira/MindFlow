package com.fabianospdev.mindflow.features.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.fabianospdev.mindflow.features.login.domain.usecases.LoginRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginRemoteUseCase
) : ViewModel() {

    fun login() {
        // Lógica do login
    }
}
