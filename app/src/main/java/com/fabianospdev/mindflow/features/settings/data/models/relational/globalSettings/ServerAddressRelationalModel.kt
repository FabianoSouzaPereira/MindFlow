package com.fabianospdev.mindflow.features.settings.data.models.relational.globalSettings

import com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings.ServerAddressEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ServerAddressRelationalModel(
    @SerialName("id")
    override val id: Long,

    @SerialName("serverUri")
    override var serverUri: String = "http://server.com",

    @SerialName("serverPort")
    override var serverPort: Int = 8080,

    @SerialName("serverUser")
    override var serverUser: String = "admin",

    @SerialName("serverPassword")
    override var serverPassword: String = "123456",
) : ServerAddressEntity