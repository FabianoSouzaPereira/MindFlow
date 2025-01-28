package com.fabianospdev.mindflow.features.login.domain.entities

import android.net.Uri

interface LoginResponseEntity {
    val token: String
    val adminClaim: Boolean?
    val photoUrl: Uri?
    val uid: String?
    val isEmailVerified: Boolean?
    val displayName: String?
}