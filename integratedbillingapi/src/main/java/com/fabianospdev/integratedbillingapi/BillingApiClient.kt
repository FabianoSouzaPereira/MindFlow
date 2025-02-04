package com.fabianospdev.integratedbillingapi

import com.fabianospdev.integratedbillingapi.models.TransactionRequest
import com.fabianospdev.integratedbillingapi.models.TransactionResponse

class BillingApiClient(private val apiService: BillingApiService) {

    fun createTransaction(request: TransactionRequest): TransactionResponse {
        return apiService.createTransaction(request)
    }
}