package com.fabianospdev.mindflow.shared.utils

import android.net.Uri
import com.fabianospdev.mindflow.features.login.data.models.LoginResponseModel

class Shared {
    companion object {
        @JvmStatic
        var instance = Shared()
    }

    init {
        instance = this
    }

    var userProfile: LoginResponseModel = LoginResponseModel(
        token = "",
        adminClaim = false,
        photoUrl = Uri.parse("https:// default. url/ image. jpg"),
        uid = "",
        isEmailVerified = false,
        displayName = ""
    )
}