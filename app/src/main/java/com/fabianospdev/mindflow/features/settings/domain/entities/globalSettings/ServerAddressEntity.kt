package com.fabianospdev.mindflow.features.settings.domain.entities.globalSettings

interface ServerAddressEntity {
    val id: Long
    var serverUri: String
    var serverPort: Int
    var serverUser: String
    var serverPassword: String
}