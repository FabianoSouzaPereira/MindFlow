package com.fabianospdev.mindflow.features.settings.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabianospdev.mindflow.MainViewModel
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.core.helpers.exceptions.CommonError
import com.fabianospdev.mindflow.core.helpers.exceptions.TimeoutException
import com.fabianospdev.mindflow.core.helpers.exceptions.UnauthorizedException
import com.fabianospdev.mindflow.core.helpers.exceptions.UserNotFoundException
import com.fabianospdev.mindflow.core.helpers.exceptions.ValidationException
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.GlobalSettingsEntity
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.ServerAddressEntity
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.copy
import com.fabianospdev.mindflow.features.settings.domain.usecases.SettingsRemoteUseCase
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.SettingsError
import com.fabianospdev.mindflow.features.settings.presentation.ui.settings.states.SettingsState
import com.fabianospdev.mindflow.shared.utils.Shared
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val mainViewModel: MainViewModel,
    private val useCase: SettingsRemoteUseCase,
    private val retryController: RetryController,
    private val appConfig: AppConfig
) : ViewModel() {

    private val _state = MutableLiveData<SettingsState>(SettingsState.SettingsLoading)
    val state: LiveData<SettingsState> get() = _state

    private val _recreateActivity = MutableSharedFlow<Unit>()
    val recreateActivity: SharedFlow<Unit> = _recreateActivity.asSharedFlow()

    private val _showRetryLimitReached = MutableStateFlow(false)
    val showRetryLimitReached: StateFlow<Boolean> get() = _showRetryLimitReached

    val isUsingFirebase: StateFlow<Boolean> = appConfig.isUsingFirebase
    val baseUrl: StateFlow<String> = appConfig.baseUrl

    /** ShowSettingsIdle params **/
    private val _id = MutableStateFlow(0L)
    private val _maintenanceMode = MutableStateFlow(false)
    private val _defaultLanguage = MutableStateFlow("")
    private val _privacyPolicyURL = MutableStateFlow("")
    private val _termsOfServiceURL = MutableStateFlow("")
    private val _appVersion = MutableStateFlow("")
    private val _featureToggle = MutableStateFlow(false)
    private val _serverAddress = MutableStateFlow<ServerAddressEntity?>(null)
    private val _supportContactEmail = MutableStateFlow("")
    private val _defaultTimezone = MutableStateFlow("")
    private val _maxUploadSize = MutableStateFlow(0L)
    private val _analyticsEnabled = MutableStateFlow(false)
    private val _chatEnabled = MutableStateFlow(false)
    private val _darkMode = MutableStateFlow(false)

    val id: StateFlow<Long> get() = _id
    val maintenanceMode: StateFlow<Boolean> get() = _maintenanceMode
    val defaultLanguage: StateFlow<String> get() = _defaultLanguage
    val privacyPolicyURL: StateFlow<String> get() = _privacyPolicyURL
    val termsOfServiceURL: StateFlow<String> get() = _termsOfServiceURL
    val appVersion: StateFlow<String> get() = _appVersion
    val featureToggle: StateFlow<Boolean> get() = _featureToggle
    val serverAddress: StateFlow<ServerAddressEntity?> get() = _serverAddress
    val supportContactEmail: StateFlow<String> get() = _supportContactEmail
    val defaultTimezone: StateFlow<String> get() = _defaultTimezone
    val maxUploadSize: StateFlow<Long> get() = _maxUploadSize
    val analyticsEnabled: StateFlow<Boolean> get() = _analyticsEnabled
    val chatEnabled: StateFlow<Boolean> get() = _chatEnabled
    val darkMode: StateFlow<Boolean> get() = _darkMode

    private val _globalSettings = MutableStateFlow<GlobalSettingsEntity?>(null)
    val globalSettings: StateFlow<GlobalSettingsEntity?> = _globalSettings.asStateFlow()

    fun getSettings() {
        if (!retryController.isRetryEnabled.value) {
            _showRetryLimitReached.value = true
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = useCase.getSettings()
                if (result.isSuccess) {
                    val entity = result.getOrNull()
                    withContext(Dispatchers.Main) {
                        _globalSettings.value = entity
                        _state.value = SettingsState.SettingsSuccess(entity = entity)
                        retryController.resetRetryCount()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        retryController.incrementRetryCount()
                        _state.value = SettingsState.SettingsError(SettingsError.DataLoadFailed.message)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _state.value = when (e) {
                        is UserNotFoundException -> SettingsState.SettingsError(SettingsError.DataLoadFailed.message)
                        is TimeoutException -> SettingsState.SettingsTimeoutError(CommonError.TimeoutError.message)
                        is UnauthorizedException -> SettingsState.SettingsUnauthorized(CommonError.Unauthorized.message)
                        is ValidationException -> SettingsState.SettingsValidationError(CommonError.ValidationError.message)
                        else -> SettingsState.SettingsError(SettingsError.SectionNotAvailable.message)
                    }
                }
            }
        }
    }

    private fun setSettings(content: GlobalSettingsEntity, userId: String) {
        if (!retryController.isRetryEnabled.value) {
            _showRetryLimitReached.value = true
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = useCase.setSettings(model = content, userId = userId)
                withContext(Dispatchers.Main) {
                    if (result.isSuccess) {
                        _state.value = SettingsState.SettingsSuccess(response = result.getOrNull())
                        retryController.resetRetryCount()
                    } else {
                        retryController.incrementRetryCount()
                        _state.value = SettingsState.SettingsError("Failed to save settings.")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    retryController.incrementRetryCount()
                    _state.value = when (e) {
                        is TimeoutException -> SettingsState.SettingsTimeoutError(CommonError.TimeoutError.message)
                        is UnauthorizedException -> SettingsState.SettingsUnauthorized(CommonError.Unauthorized.message)
                        is ValidationException -> SettingsState.SettingsValidationError(CommonError.ValidationError.message)
                        else -> SettingsState.SettingsError("Unexpected error when saving settings.")
                    }
                }
            }
        }
    }

    private fun updateSettings() {
        globalSettings.value?.let {
            setSettings(
                it,
                userId = Shared.instance.userProfile.uid.toString()
            )
        }
        getSettings()
    }

    fun setIdleState() {
        _state.value = SettingsState.SettingsIdle()
    }

    fun toggleFirebaseUsage(enabled: Boolean) {
        //  appConfig.setUsingFirebase(enabled)
        _globalSettings.value = _globalSettings.value?.copy(featureToggle = enabled)
        updateSettings()
    }

    fun setMaintenanceMode(enabled: Boolean) {
        _globalSettings.value = _globalSettings.value?.copy(maintenanceMode = enabled)
        updateSettings()
    }

    fun setAnalyticsEnabled(enabled: Boolean) {
        _globalSettings.value = _globalSettings.value?.copy(analyticsEnabled = enabled)
        updateSettings()
    }

    fun setChatEnabled(enabled: Boolean) {
        _globalSettings.value = _globalSettings.value?.copy(chatEnabled = enabled)
        updateSettings()
    }

    fun setDarkMode(enabled: Boolean) {
        _globalSettings.value = _globalSettings.value?.copy(darkMode = enabled)
        updateSettings()

        _darkMode.value = enabled
        mainViewModel.requestRecreate()
    }

    fun resetRetryLimitNotification() {
        _showRetryLimitReached.value = false
        retryController.resetRetryCount()
    }

    fun resetState() {
        _state.value = SettingsState.SettingsIdle()
    }

    fun clearInputFields() {}
}
