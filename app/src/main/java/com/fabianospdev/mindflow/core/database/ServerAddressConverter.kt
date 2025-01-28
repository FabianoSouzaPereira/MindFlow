package com.fabianospdev.mindflow.core.database

import androidx.room.TypeConverter
import com.fabianospdev.mindflow.core.database.entities.ServerAddress
import com.google.gson.Gson

class ServerAddressConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromServerAddress(serverAddress: ServerAddress?): String? {
        return gson.toJson(serverAddress)
    }

    @TypeConverter
    fun toServerAddress(json: String?): ServerAddress? {
        return if (json.isNullOrEmpty()) null else gson.fromJson(json, ServerAddress::class.java)
    }
}
