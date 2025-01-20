package com.fabianospdev.mindflow.features.settings.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.core.helpers.exceptions.CommonError
import com.fabianospdev.mindflow.features.settings.data.models.relational.globalSettings.GlobalSettingsRelationalModel
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.GlobalSettingsEntity
import com.fabianospdev.mindflow.features.settings.domain.usecases.SettingsRemoteUseCase
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.SettingsError
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.states.SettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val useCase: SettingsRemoteUseCase,
    private val retryController: RetryController,
    private val appConfig: AppConfig
) : ViewModel() {
    private val _state = MutableLiveData<SettingsState>(SettingsState.SettingsIdle)
    val state: LiveData<SettingsState> get() = _state

    private val _showRetryLimitReached = MutableStateFlow(false)
    val showRetryLimitReached: StateFlow<Boolean> get() = _showRetryLimitReached
    val isUsingFirebase: StateFlow<Boolean> = appConfig.isUsingFirebase


    fun getSettings() {
        if (!retryController.isRetryEnabled.value) {
            _showRetryLimitReached.value = true
            return
        }
        _state.value = SettingsState.SettingsLoading

        viewModelScope.launch {
            try {

                val result = useCase.getSettings()
                if (result.isSuccess) {
                    result.getOrNull()?.let { entity ->
                        _state.value = SettingsState.SettingsSuccess(entity = entity, null)
                        retryController.resetRetryCount()
                    } ?: run {
                        _state.value = SettingsState.SettingsError("Resposta nula")
                    }
                } else {
                    retryController.incrementRetryCount()
                    _state.value = SettingsState.SettingsError(SettingsError.DataLoadFailed.message)
                }

            } catch (e: Exception) {
                retryController.incrementRetryCount()
                _state.value = when (e) {
                    is com.fabianospdev.mindflow.core.helpers.exceptions.UserNotFoundException -> SettingsState.SettingsError(
                        SettingsError.DataLoadFailed.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.TimeoutException -> SettingsState.SettingsTimeoutError(
                        CommonError.TimeoutError.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.UnauthorizedException -> SettingsState.SettingsUnauthorized(
                        CommonError.Unauthorized.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.ValidationException -> SettingsState.SettingsValidationError(
                        CommonError.ValidationError.message
                    )

                    else -> SettingsState.SettingsError(SettingsError.SectionNotAvailable.message)
                }
            }
        }
    }

    fun setSettings(configuration: GlobalSettingsEntity) {

        if (!retryController.isRetryEnabled.value) {
            _showRetryLimitReached.value = true
            return
        }
        _state.value = SettingsState.SettingsLoading

        viewModelScope.launch {
            try {
                val result = useCase.setSettings(model = configuration)
                if (result.isSuccess) {
                    _state.value = SettingsState.SettingsSuccess(entity = null, response = result.getOrNull())
                    retryController.resetRetryCount()
                } else {
                    retryController.incrementRetryCount()
                    _state.value = SettingsState.SettingsError("Falha ao salvar as configurações.")
                }
            } catch (e: Exception) {
                retryController.incrementRetryCount()
                _state.value = when (e) {
                    is com.fabianospdev.mindflow.core.helpers.exceptions.TimeoutException -> SettingsState.SettingsTimeoutError(
                        CommonError.TimeoutError.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.UnauthorizedException -> SettingsState.SettingsUnauthorized(
                        CommonError.Unauthorized.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.ValidationException -> SettingsState.SettingsValidationError(
                        CommonError.ValidationError.message
                    )

                    else -> SettingsState.SettingsError("Erro inesperado ao salvar as configurações.")
                }
            }
        }
    }

    fun setSettings(configuration: GlobalSettingsRelationalModel) {

        if (!retryController.isRetryEnabled.value) {
            _showRetryLimitReached.value = true
            return
        }
        _state.value = SettingsState.SettingsLoading

        viewModelScope.launch {
            try {
                val result = useCase.setSettings(model = configuration)
                if (result.isSuccess) {
                    _state.value = SettingsState.SettingsSuccess(entity = null, response = result.getOrNull())
                    retryController.resetRetryCount()
                } else {
                    retryController.incrementRetryCount()
                    _state.value = SettingsState.SettingsError("Falha ao salvar as configurações.")
                }
            } catch (e: Exception) {
                retryController.incrementRetryCount()
                _state.value = when (e) {
                    is com.fabianospdev.mindflow.core.helpers.exceptions.TimeoutException -> SettingsState.SettingsTimeoutError(
                        CommonError.TimeoutError.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.UnauthorizedException -> SettingsState.SettingsUnauthorized(
                        CommonError.Unauthorized.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.ValidationException -> SettingsState.SettingsValidationError(
                        CommonError.ValidationError.message
                    )

                    else -> SettingsState.SettingsError("Erro inesperado ao salvar as configurações.")
                }
            }
        }
    }

    fun setIdleState() {
        _state.value = SettingsState.SettingsIdle
    }

    fun toggleFirebaseUsage() {
        appConfig.setUsingFirebase(!isUsingFirebase.value)
    }

    fun resetRetryLimitNotification() {
        _showRetryLimitReached.value = false
        retryController.resetRetryCount()
    }

    fun resetState() {
        _state.value = SettingsState.SettingsIdle
    }

    fun clearInputFields() {}
}
