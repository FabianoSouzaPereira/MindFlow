package com.fabianospdev.mindflow.features.settings.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.home.presentation.ui.home.states.HomeState
import com.fabianospdev.mindflow.features.settings.domain.usecases.SettingsRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val useCase: SettingsRemoteUseCase,
    private val retryController: RetryController,
    private val appConfig: AppConfig
) : ViewModel() {
    private val _state = MutableLiveData<HomeState>(HomeState.HomeIdle)
    val state: LiveData<HomeState> get() = _state

    private val _showRetryLimitReached = MutableStateFlow(false)
    val showRetryLimitReached: StateFlow<Boolean> get() = _showRetryLimitReached
    val isUsingFirebase: StateFlow<Boolean> = appConfig.isUsingFirebase

    fun toggleFirebaseUsage() {
        appConfig.setUsingFirebase(!isUsingFirebase.value)
    }

    fun resetRetryLimitNotification() {
        _showRetryLimitReached.value = false
        retryController.resetRetryCount()
    }

    fun resetState() {
        _state.value = HomeState.HomeIdle
    }

    fun clearInputFields() {}
}
