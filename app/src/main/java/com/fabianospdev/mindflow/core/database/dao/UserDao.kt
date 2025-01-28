package com.fabianospdev.mindflow.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fabianospdev.mindflow.core.database.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun getUserById(userId: Long): User?

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user WHERE id = :userId")
    suspend fun deleteUserById(userId: Long)
}
