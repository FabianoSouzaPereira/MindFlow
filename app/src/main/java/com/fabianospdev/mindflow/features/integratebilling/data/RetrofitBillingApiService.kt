package com.fabianospdev.mindflow.features.integratebilling.data

import com.fabianospdev.integratedbillingapi.BillingApiService
import com.fabianospdev.integratedbillingapi.PagarMeApi
import com.fabianospdev.integratedbillingapi.models.TransactionRequest
import com.fabianospdev.integratedbillingapi.models.TransactionResponse
import retrofit2.Retrofit

class RetrofitBillingApiService(private val retrofit: Retrofit) : BillingApiService {

    override fun createTransaction(request: TransactionRequest): TransactionResponse {
        val api = retrofit.create(PagarMeApi::class.java)

        return api.createTransaction(request).execute().body()!!
    }
}