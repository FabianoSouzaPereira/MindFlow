package com.fabianospdev.mindflow.features.user_profile.presentation.ui.profile.states

import com.fabianospdev.mindflow.features.user_profile.domain.entities.ProfileResponseEntity

sealed class ProfileState {
    object ProfileLoading : ProfileState()
    object ProfileIdle : ProfileState()
    data class ProfileSuccess(val response: ProfileResponseEntity) : ProfileState()
    data class ProfileError(val error: String) : ProfileState()
    data class ProfileNoConnection(val errorMessage: String) : ProfileState()
    data class ProfileValidationError(val message: String) : ProfileState()
    data class ProfileTimeoutError(val message: String) : ProfileState()
    data class ProfileUnauthorized(val message: String) : ProfileState()
    data class ProfileUnknown(val message: String) : ProfileState()
}