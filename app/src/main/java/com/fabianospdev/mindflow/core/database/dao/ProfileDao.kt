package com.fabianospdev.mindflow.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fabianospdev.mindflow.core.database.entities.Profile

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile")
    fun getAll(): List<Profile>

    @Query("SELECT * FROM profile WHERE id IN (:profileIds)")
    fun loadAllByIds(profileIds: IntArray): List<Profile>

    @Query(
        "SELECT * FROM profile WHERE email LIKE :email"
    )
    fun findByName(email: String): Profile

    @Insert
    fun insertAll(vararg profiles: Profile)

    @Delete
    fun delete(profile: Profile)
}