package com.fabianospdev.mindflow.features.user_profile.domain.entities

import java.time.LocalDate
import java.time.LocalDateTime

interface ProfileEntity {
    val id: Long
    val fullName: String
    val username: String
    val email: String
    val profilePictureUrl: String?
    val birthDate: LocalDate?
    val gender: String?
    val location: String?
    val status: String?
    val bio: String?
    val socialLinks: Map<String, String>
    val lastUpdated: LocalDateTime
    val preferences: Map<String, String>
    val isEmailVerified: Boolean
}