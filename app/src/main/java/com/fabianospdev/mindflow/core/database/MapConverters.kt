package com.fabianospdev.mindflow.core.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromMap(map: Map<String, String>?): String? {
        return gson.toJson(map)
    }

    @TypeConverter
    fun toMap(json: String?): Map<String, String>? {
        return if (json.isNullOrEmpty()) {
            emptyMap()
        } else {
            val type = object : TypeToken<Map<String, String>>() {}.type
            gson.fromJson(json, type)
        }
    }
}
