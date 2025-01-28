package com.fabianospdev.mindflow.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fabianospdev.mindflow.core.database.dao.ProfileDao
import com.fabianospdev.mindflow.core.database.dao.SettingsDao
import com.fabianospdev.mindflow.core.database.dao.UserDao
import com.fabianospdev.mindflow.core.database.entities.Profile
import com.fabianospdev.mindflow.core.database.entities.ServerAddress
import com.fabianospdev.mindflow.core.database.entities.Settings
import com.fabianospdev.mindflow.core.database.entities.User

@Database(entities = [Profile::class, Settings::class, ServerAddress::class, User::class], version = 1)
@TypeConverters(DateTimeConverters::class, MapConverters::class, ServerAddressConverter::class, RolesConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun settingsDao(): SettingsDao
    abstract fun userDao(): UserDao
}