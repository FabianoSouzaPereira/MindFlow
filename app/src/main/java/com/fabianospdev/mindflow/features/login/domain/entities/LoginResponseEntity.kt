package com.fabianospdev.mindflow.features.login.domain.entities

interface LoginResponseEntity {
    val token: String
    val adminClaim: Boolean?
}