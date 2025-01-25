package com.fabianospdev.mindflow.features.profile.presentation.ui.profile.states

import com.fabianospdev.mindflow.features.profile.data.model.ProfileResponseEntity
import com.fabianospdev.mindflow.features.profile.domain.entities.ProfileEntity

sealed class ProfileState {
    object ProfileLoading : ProfileState()

    object ProfileIdle : ProfileState()

    data class ProfileSuccess(
        val profile: ProfileEntity? = null,
        val profileResponse: ProfileResponseEntity? = null
    ) : ProfileState()

    data class ProfileError(val error: String) : ProfileState() {
        fun isNetworkRelated(): Boolean {
            return error.contains("network", ignoreCase = true)
        }
    }

    data class ProfileNoConnection(val errorMessage: String) : ProfileState()

    data class ProfileValidationError(val message: String) : ProfileState()

    data class ProfileTimeoutError(val message: String) : ProfileState()

    data class ProfileUnauthorized(val message: String) : ProfileState()

    data class ProfileUnknown(val message: String) : ProfileState() {

        fun isLoginUnknown(): Boolean {
            return message.isNotEmpty()
        }

        override fun toString(): String {
            return "Error unknown occurred with message: $message"
        }
    }
}