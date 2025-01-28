package com.fabianospdev.mindflow.features.login.domain.repositories

import com.fabianospdev.mindflow.core.database.entities.User

interface LoginLocalRepository {
    suspend fun saveLocalUser(user: User)
    suspend fun getUserById(userId: Long): User?
    suspend fun getAllUsers(): List<User>
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun deleteUserById(userId: Long)
}