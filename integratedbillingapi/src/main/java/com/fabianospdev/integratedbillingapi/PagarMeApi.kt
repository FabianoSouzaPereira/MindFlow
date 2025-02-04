package com.fabianospdev.integratedbillingapi

import com.fabianospdev.integratedbillingapi.models.TransactionRequest
import com.fabianospdev.integratedbillingapi.models.TransactionResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PagarMeApi {
    @POST("transactions")
    fun createTransaction(@Body request: TransactionRequest): Call<TransactionResponse>
}