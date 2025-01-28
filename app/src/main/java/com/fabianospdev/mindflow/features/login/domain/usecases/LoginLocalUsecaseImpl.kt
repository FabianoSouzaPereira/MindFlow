package com.fabianospdev.mindflow.features.login.domain.usecases

import com.fabianospdev.mindflow.core.database.entities.User
import com.fabianospdev.mindflow.features.login.domain.repositories.LoginLocalRepository

class LoginLocalUsecaseImpl(
    private val loginLocalRepository: LoginLocalRepository
) : LoginLocalUsecase {

    override suspend fun saveLocalUser(user: User) {
        return loginLocalRepository.saveLocalUser(user)
    }

    override suspend fun getUserById(userId: Long): User? {
        return loginLocalRepository.getUserById(userId)
    }

    override suspend fun getAllUsers(): List<User> {
        return loginLocalRepository.getAllUsers()
    }

    override suspend fun updateUser(user: User) {
        return loginLocalRepository.updateUser(user)
    }

    override suspend fun deleteUser(user: User) {
        return loginLocalRepository.deleteUser(user)
    }

    override suspend fun deleteUserById(userId: Long) {
        return loginLocalRepository.deleteUserById(userId)
    }
}