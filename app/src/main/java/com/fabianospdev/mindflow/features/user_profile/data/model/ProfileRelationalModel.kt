package com.fabianospdev.mindflow.features.user_profile.data.model

import com.fabianospdev.mindflow.features.user_profile.domain.entities.ProfileEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileRelationalModel(
    @SerialName("id") override val id: Long,

    @SerialName("fullName") override val fullName: String,

    @SerialName("username") override val username: String,

    @SerialName("email") override val email: String,

    @SerialName("profilePictureUrl") override val profilePictureUrl: String? = null,

    @SerialName("birthDate") override val birthDate: LocalDate? = null,

    @SerialName("gender") override val gender: String? = null,

    @SerialName("location") override val location: String? = null,

    @SerialName("status") override val status: String? = null,

    @SerialName("bio") override val bio: String? = null,

    @SerialName("socialLinks") override val socialLinks: Map<String, String> = emptyMap(),

    @SerialName("lastUpdated") override val lastUpdated: LocalDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()),

    @SerialName("preferences") override val preferences: Map<String, String> = emptyMap(),

    @SerialName("isEmailVerified") override val isEmailVerified: Boolean = false
) : ProfileEntity