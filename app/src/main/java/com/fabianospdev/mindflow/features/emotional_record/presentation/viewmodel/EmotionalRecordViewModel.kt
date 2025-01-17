package com.fabianospdev.mindflow.features.emotional_record.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmotionalRecordViewModel @Inject constructor(private val useCase: EmotionalRecordRemoteUseCase) : ViewModel()
