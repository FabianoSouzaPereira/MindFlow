package com.fabianospdev.mindflow.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fabianospdev.mindflow.core.database.entities.Settings

@Dao
interface SettingsDao {

    @Query("SELECT * FROM settings")
    fun getAll(): List<Settings>

    @Query("SELECT * FROM settings WHERE id IN (:settingsIds)")
    fun loadAllByIds(settingsIds: IntArray): List<Settings>

    @Query(
        "SELECT * FROM settings WHERE id LIKE :id"
    )
    fun findByName(id: Long): Settings

    @Insert
    fun insertAll(vararg settings: Settings)

    @Delete
    fun delete(settings: Settings)
}