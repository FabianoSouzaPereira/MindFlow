package com.fabianospdev.mindflow.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "serveraddress")
data class ServerAddress(
    @PrimaryKey val id: Long,
    var serverUri: String,
    var serverPort: Int,
    var serverUser: String,
    var serverPassword: String,
)
