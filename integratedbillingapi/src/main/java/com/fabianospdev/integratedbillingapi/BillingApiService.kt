package com.fabianospdev.integratedbillingapi

import com.fabianospdev.integratedbillingapi.models.TransactionRequest
import com.fabianospdev.integratedbillingapi.models.TransactionResponse

interface BillingApiService {
    fun createTransaction(request: TransactionRequest): TransactionResponse
}