package com.fabianospdev.mindflow.features.login.data.repositories

import com.fabianospdev.mindflow.core.database.dao.UserDao
import com.fabianospdev.mindflow.core.database.entities.User
import com.fabianospdev.mindflow.features.login.domain.repositories.LoginLocalRepository

class LoginLocalRepositoryImpl(private val userDao: UserDao) : LoginLocalRepository {
    override suspend fun saveLocalUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun getUserById(userId: Long): User? {
        return userDao.getUserById(userId)
    }

    override suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    override suspend fun updateUser(user: User) {
        return userDao.updateUser(user)
    }

    override suspend fun deleteUser(user: User) {
        return userDao.deleteUser(user)
    }

    override suspend fun deleteUserById(userId: Long) {
        return userDao.deleteUserById(userId)
    }
}