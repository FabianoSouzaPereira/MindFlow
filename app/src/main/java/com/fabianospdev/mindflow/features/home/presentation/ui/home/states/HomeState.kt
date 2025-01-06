package com.fabianospdev.mindflow.features.home.presentation.ui.home.states

import com.fabianospdev.mindflow.features.home.domain.entities.HomeResponseEntity


sealed class HomeState {
    object HomeLoading : HomeState()
    object HomeIdle : HomeState()
    data class HomeSuccess(val response: HomeResponseEntity) : HomeState()
    data class HomeError(val error: String) : HomeState()
    data class HomeNoConnection(val errorMessage: String) : HomeState()
    data class HomeValidationError(val message: String) : HomeState()
    data class HomeTimeoutError(val message: String) : HomeState()
    data class HomeUnauthorized(val message: String) : HomeState()
    data class HomeUnknown(val message: String) : HomeState()
}
