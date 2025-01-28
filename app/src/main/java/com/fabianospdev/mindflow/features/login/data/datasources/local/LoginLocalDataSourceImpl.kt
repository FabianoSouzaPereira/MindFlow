package com.fabianospdev.mindflow.features.login.data.datasources.local

import com.fabianospdev.mindflow.core.database.dao.UserDao
import com.fabianospdev.mindflow.core.database.entities.User

class LoginLocalDataSourceImpl(private val userDao: UserDao) : LoginLocalDataSource {
    override suspend fun saveLocalUser(user: User) {
        return userDao.insertUser(user)
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