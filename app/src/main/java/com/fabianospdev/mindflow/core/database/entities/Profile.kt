package com.fabianospdev.mindflow.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey val id: Long,
    val fullName: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String?,
    val birthDate: LocalDate?,
    val gender: String?,
    val location: String?,
    val status: String?,
    val bio: String?,
    val socialLinks: Map<String, String>,
    val lastUpdated: LocalDateTime,
    val preferences: Map<String, String>,
    val isEmailVerified: Boolean
)