package com.fabianospdev.mindflow.features.emotional_record.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.CleanableViewModel
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.core.helpers.exceptions.CommonError
import com.fabianospdev.mindflow.features.emotional_record.domain.usecases.EmotionalRecordRemoteUseCase
import com.fabianospdev.mindflow.features.emotional_record.presentation.ui.emotional_record.EmotionRecordError.DataLoadFailed
import com.fabianospdev.mindflow.features.emotional_record.presentation.ui.emotional_record.EmotionRecordError.SectionNotAvailable
import com.fabianospdev.mindflow.features.emotional_record.presentation.ui.emotional_record.states.EmotionalRecordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmotionalRecordViewModel @Inject constructor(
    private val useCase: EmotionalRecordRemoteUseCase,
    private val retryController: RetryController,
    private val appConfig: AppConfig
) : ViewModel(), CleanableViewModel {

    private val _state = MutableLiveData<EmotionalRecordState>(EmotionalRecordState.EmotionalRecordIdle)
    val state: LiveData<EmotionalRecordState> get() = _state

    private val _showRetryLimitReached = MutableStateFlow(false)
    val showRetryLimitReached: StateFlow<Boolean> get() = _showRetryLimitReached
    val isUsingFirebase: StateFlow<Boolean> = appConfig.isUsingFirebase

    fun getEmotionRecords() {
        if (!retryController.isRetryEnabled.value) {
            _showRetryLimitReached.value = true
            return
        }
        _state.value = EmotionalRecordState.EmotionalRecordIdle
    }

    fun toggleFirebaseUsage() {
        appConfig.setUsingFirebase(!appConfig.isUsingFirebase.value)
    }


    override fun clearInputFields() {
        _showRetryLimitReached.value = false
        retryController.resetRetryCount()
    }

    override fun resetState() {
        _state.value = EmotionalRecordState.EmotionalRecordIdle

        viewModelScope.launch {
            try {
                delay(2000) //todo remove it

                val result = useCase.getEmotionalRecordContent()
                if (result.isSuccess) {
                    result.getOrNull()?.let { entity ->
                        _state.value = EmotionalRecordState.EmotionalRecordSuccess(entity)
                        retryController.resetRetryCount()
                    } ?: run {
                        _state.value = EmotionalRecordState.EmotionalRecordError("Resposta nula")
                    }
                } else {
                    retryController.incrementRetryCount()
                    _state.value = EmotionalRecordState.EmotionalRecordError(DataLoadFailed.message)
                }

            } catch (e: Exception) {
                retryController.incrementRetryCount()
                _state.value = when (e) {
                    is com.fabianospdev.mindflow.core.helpers.exceptions.UserNotFoundException -> EmotionalRecordState.EmotionalRecordError(
                        DataLoadFailed.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.TimeoutException -> EmotionalRecordState.EmotionalRecordTimeoutError(
                        CommonError.TimeoutError.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.UnauthorizedException -> EmotionalRecordState.EmotionalRecordUnauthorized(
                        CommonError.Unauthorized.message
                    )

                    is com.fabianospdev.mindflow.core.helpers.exceptions.ValidationException -> EmotionalRecordState.EmotionalRecordValidationError(
                        CommonError.ValidationError.message
                    )

                    else -> EmotionalRecordState.EmotionalRecordError(SectionNotAvailable.message)
                }
            }
        }
    }
}
