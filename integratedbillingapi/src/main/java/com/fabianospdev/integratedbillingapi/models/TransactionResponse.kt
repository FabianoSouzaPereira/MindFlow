package com.fabianospdev.integratedbillingapi.models

data class TransactionResponse(
    val status: String,
    val tid: String?,
    val authorization_code: String?,
    val message: String?
)