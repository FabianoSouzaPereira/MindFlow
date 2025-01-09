package com.fabianospdev.mindflow.features.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.CleanableViewModel
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.core.helpers.exceptions.CommonError
import com.fabianospdev.mindflow.features.home.domain.usecases.HomeRemoteUseCase
import com.fabianospdev.mindflow.features.home.presentation.ui.home.HomeError
import com.fabianospdev.mindflow.features.home.presentation.ui.home.states.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeRemoteUseCase,
    private val retryController: RetryController,
    private val appConfig: AppConfig
) : ViewModel(), CleanableViewModel {

    private val _showRetryLimitReached = MutableStateFlow(false)
    val showRetryLimitReached: StateFlow<Boolean> get() = _showRetryLimitReached
    val isUsingFirebase: StateFlow<Boolean> = appConfig.isUsingFirebase

    private val _state = MutableLiveData<HomeState>(HomeState.HomeIdle)
    val state: LiveData<HomeState> get() = _state


    fun toggleFirebaseUsage() {
        appConfig.setUsingFirebase(!appConfig.isUsingFirebase.value)
    }

    fun getHome(){
        if (!retryController.isRetryEnabled.value) {
            _showRetryLimitReached.value = true
            return
        }
        _state.value = HomeState.HomeLoading

        viewModelScope.launch {
            try {
                delay(2000) //todo remove it

                val result = useCase.getHomeContent()
                if (result.isSuccess) {
                    result.getOrNull()?.let { HomeEntity ->
                        _state.value = HomeState.HomeSuccess(HomeEntity)
                        retryController.resetRetryCount()
                    } ?: run {
                        _state.value = HomeState.HomeError("Resposta nula")
                    }
                } else {
                    retryController.incrementRetryCount()
                    _state.value = HomeState.HomeError(HomeError.DataLoadFailed.message)
                }

            } catch (e: Exception){
                retryController.incrementRetryCount()
                _state.value = when (e){
                    is com.fabianospdev.mindflow.core.helpers.exceptions.UserNotFoundException-> HomeState.HomeError(
                        HomeError.DataLoadFailed.message
                    )
                    is com.fabianospdev.mindflow.core.helpers.exceptions.TimeoutException -> HomeState.HomeTimeoutError(
                        CommonError.TimeoutError.message
                    )
                    is com.fabianospdev.mindflow.core.helpers.exceptions.UnauthorizedException -> HomeState.HomeUnauthorized(
                        CommonError.Unauthorized.message
                    )
                    is com.fabianospdev.mindflow.core.helpers.exceptions.ValidationException -> HomeState.HomeValidationError(
                        CommonError.ValidationError.message
                    )
                    else -> HomeState.HomeError(HomeError.SectionNotAvailable.message)
                }
            }
        }
    }

    fun resetRetryLimitNotification() {
        _showRetryLimitReached.value = false
        retryController.resetRetryCount()
    }

    override fun resetState() {
        _state.value = HomeState.HomeIdle
    }

    override fun clearInputFields() {}
}