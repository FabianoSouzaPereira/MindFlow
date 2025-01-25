package com.fabianospdev.mindflow.features.profile.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.core.helpers.exceptions.CommonError
import com.fabianospdev.mindflow.features.profile.domain.entities.ProfileEntity
import com.fabianospdev.mindflow.features.profile.domain.usecases.ProfileRemoteUseCase
import com.fabianospdev.mindflow.features.profile.presentation.ui.profile.ProfileError
import com.fabianospdev.mindflow.features.profile.presentation.ui.profile.states.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCase: ProfileRemoteUseCase,
    private val retryController: RetryController,
    private val appConfig: AppConfig
) : ViewModel() {
    private val _state = MutableLiveData<ProfileState>(ProfileState.ProfileIdle)
    val state: LiveData<ProfileState> get() = _state

    private val _showRetryLimitReached = MutableStateFlow(false)
    val showRetryLimitReached: StateFlow<Boolean> get() = _showRetryLimitReached
    val isUsingFirebase: StateFlow<Boolean> = appConfig.isUsingFirebase

    fun getProfile() {
        if (!retryController.isRetryEnabled.value) {
            _showRetryLimitReached.value = true
            return
        }
        _state.value = ProfileState.ProfileLoading

        viewModelScope.launch {
            try {

                val result = useCase.getProfileContent()
                if (result.isSuccess) {
                    result.getOrNull()?.let { entity ->
                        _state.value = ProfileState.ProfileSuccess(entity)
                        retryController.resetRetryCount()
                    } ?: run {
                        _state.value = ProfileState.ProfileError("Null response")
                    }
                } else {
                    retryController.incrementRetryCount()
                    _state.value = ProfileState.ProfileError(ProfileError.DataLoadFailed.message)
                }

            } catch (e: Exception) {
                retryController.incrementRetryCount()
                _state.value = when (e) {
                    is com.fabianospdev.mindflow.core.helpers.exceptions.UserNotFoundException -> ProfileState.ProfileError(
                        ProfileError.DataLoadFailed.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.TimeoutException -> ProfileState.ProfileTimeoutError(
                        CommonError.TimeoutError.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.UnauthorizedException -> ProfileState.ProfileUnauthorized(
                        CommonError.Unauthorized.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.ValidationException -> ProfileState.ProfileValidationError(
                        CommonError.ValidationError.message
                    )

                    else -> ProfileState.ProfileError(ProfileError.SectionNotAvailable.message)
                }
            }
        }
    }

    fun setProfileContent(model: ProfileEntity) {

        if (!retryController.isRetryEnabled.value) {
            _showRetryLimitReached.value = true
            return
        }
        _state.value = ProfileState.ProfileLoading

        viewModelScope.launch {
            try {
                val result = useCase.setProfileContent(model = model)
                if (result.isSuccess) {
                    _state.value = ProfileState.ProfileSuccess(profileResponse = result.getOrThrow())
                    retryController.resetRetryCount()
                } else {
                    retryController.incrementRetryCount()
                    _state.value = ProfileState.ProfileError("Falha ao salvar as configurações.")
                }
            } catch (e: Exception) {
                retryController.incrementRetryCount()
                _state.value = when (e) {
                    is com.fabianospdev.mindflow.core.helpers.exceptions.TimeoutException -> ProfileState.ProfileTimeoutError(
                        CommonError.TimeoutError.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.UnauthorizedException -> ProfileState.ProfileUnauthorized(
                        CommonError.Unauthorized.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.ValidationException -> ProfileState.ProfileValidationError(
                        CommonError.ValidationError.message
                    )

                    else -> ProfileState.ProfileError("Erro inesperado ao salvar o perfil.")
                }
            }
        }
    }

    fun toggleFirebaseUsage() {
        appConfig.setUsingFirebase(!isUsingFirebase.value)
    }

    fun resetRetryLimitNotification() {
        _showRetryLimitReached.value = false
        retryController.resetRetryCount()
    }

    fun resetState() {
        _state.value = ProfileState.ProfileIdle
    }

    fun clearInputFields() {}
}
