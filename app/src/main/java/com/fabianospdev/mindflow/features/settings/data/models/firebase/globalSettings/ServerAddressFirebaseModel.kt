package com.fabianospdev.mindflow.features.settings.data.models.firebase.globalSettings

import com.fabianospdev.mindflow.core.database.entities.ServerAddress
import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.ServerAddressEntity

data class ServerAddressFirebaseModel(
    override val id: Long = 0,
    override var serverUri: String = "http://server.com",
    override var serverPort: Int = 8080,
    override var serverUser: String = "admin",
    override var serverPassword: String = "123456",
) : ServerAddressEntity

fun ServerAddressFirebaseModel.toEntity(): ServerAddress {
    return ServerAddress(
        id = this.id,
        serverUri = this.serverUri,
        serverPort = this.serverPort,
        serverUser = serverUser,
        serverPassword = serverPassword
    )
}