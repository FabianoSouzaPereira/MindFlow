package com.fabianospdev.mindflow.features.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabianospdev.mindflow.core.helpers.AppConfig
import com.fabianospdev.mindflow.core.helpers.RetryController
import com.fabianospdev.mindflow.features.home.domain.usecases.HomeRemoteUseCase
import com.fabianospdev.mindflow.features.home.presentation.ui.home.states.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeRemoteUseCase,
    private val retryController: RetryController,
    private val appConfig: AppConfig
) : ViewModel() {
    private val _state = MutableLiveData<HomeState>(HomeState.HomeIdle)
    val state: LiveData<HomeState> get() = _state
}