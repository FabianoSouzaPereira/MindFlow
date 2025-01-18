package com.fabianospdev.mindflow.features.user_profile.data.model

import com.fabianospdev.mindflow.features.user_profile.domain.entities.ProfileEntity
import java.time.LocalDate
import java.time.LocalDateTime

class ProfileModel(
    override val id: Long,
    override val fullName: String,
    override val username: String,
    override val email: String,
    override val profilePictureUrl: String? = null,
    override val birthDate: LocalDate? = null,
    override val gender: String? = null,
    override val location: String? = null,
    override val status: String? = null,
    override val bio: String? = null,
    override val socialLinks: Map<String, String> = emptyMap(),
    override val lastUpdated: LocalDateTime = LocalDateTime.now(),
    override val preferences: Map<String, String> = emptyMap(),
    override val isEmailVerified: Boolean = false
) : ProfileEntity