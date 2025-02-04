package com.fabianospdev.mindflow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.login.domain.usecases.LoginLocalUsecase
import com.fabianospdev.mindflow.features.settings.domain.usecases.SettingsRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsUseCase: SettingsRemoteUseCase,
    private val loginLocalUsecase: LoginLocalUsecase,
    private val retryController: RetryController
) : ViewModel() {

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode = _isDarkMode.asStateFlow()

    private val _recreateEvent = MutableSharedFlow<Unit>()
    val recreateEvent: SharedFlow<Unit> = _recreateEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            try {
                val result = settingsUseCase.getSettings()

                if (result.isSuccess) {
                    _isDarkMode.value = result.getOrNull()?.darkMode ?: false
                    retryController.resetRetryCount()
                } else {
                    retryController.incrementRetryCount()
                    throw Throwable("Fail result")

                }
            } catch (e: Exception) {
                throw Throwable("error")
            }
        }
    }

    fun requestRecreate() {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            Log.d("MainViewModel", "Emitindo evento recreateEvent")
            _recreateEvent.emit(Unit)
        }
    }
}
