package com.fabianospdev.mindflow.features.home.presentation.ui.home.states

import com.fabianospdev.mindflow.features.home.domain.entities.HomeResponseEntity


sealed class HomeState {
    object HomeLoading : HomeState()

    object HomeIdle : HomeState()

    data class HomeSuccess(val response: HomeResponseEntity) : HomeState()

    data class HomeError(val error: String) : HomeState() {
        fun isNetworkRelated(): Boolean {
            return error.contains("network", ignoreCase = true)
        }
    }

    data class HomeNoConnection(val errorMessage: String) : HomeState()

    data class HomeValidationError(val message: String) : HomeState()

    data class HomeTimeoutError(val message: String) : HomeState()

    data class HomeUnauthorized(val message: String) : HomeState()

    data class HomeUnknown(val message: String) : HomeState() {
        fun isHomeUnknown(): Boolean {
            return message.isNotEmpty()
        }

        override fun toString(): String {
            return "Error unknown occurred with message: $message"
        }
    }
}
