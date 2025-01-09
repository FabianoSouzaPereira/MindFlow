package com.fabianospdev.mindflow.shared.entities


interface UserEntity {
    val id: String
    val username: String
    val email: String
    val profile: UserProfileEntity
    val settings: UserSettingsEntity
}
