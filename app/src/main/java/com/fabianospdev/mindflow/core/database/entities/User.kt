package com.fabianospdev.mindflow.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val uid: String,
    val email: String,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime?,
    val lastLogin: LocalDateTime?,
    val roles: List<String> = listOf("user"),
    val isPremium: Boolean = false
)