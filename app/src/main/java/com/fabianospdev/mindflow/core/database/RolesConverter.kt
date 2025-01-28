package com.fabianospdev.mindflow.core.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class RolesConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromRoles(roles: List<String>?): String? {
        return gson.toJson(roles)
    }

    @TypeConverter
    fun toRoles(json: String?): List<String>? {
        return if (json.isNullOrEmpty()) emptyList() else gson.fromJson(json, Array<String>::class.java).toList()
    }
}
