package com.fabianospdev.integratedbillingapi

import com.fabianospdev.integratedbillingapi.models.TransactionRequest
import com.fabianospdev.integratedbillingapi.models.TransactionResponse
import jdk.vm.ci.code.site.Call
import retrofit2.Callback
import retrofit2.Response

fun createCreditCardTransaction() {
    val apiKey = "sua_api_key_aqui"
    val request = TransactionRequest(
        api_key = apiKey,
        amount = 10000, // R$ 100,00
        card_number = "4111111111111111",
        card_expiration_date = "1225",
        card_cvv = "123",
        card_holder_name = "João da Silva"
    )

    val api = RetrofitClient.instance.create(PagarMeApi::class.java)
    api.createTransaction(request).enqueue(object : Callback<TransactionResponse> {
        override fun onResponse(
            call: Call<TransactionResponse>,
            response: Response<TransactionResponse>
        ) {
            if (response.isSuccessful) {
                val transaction = response.body()
                if (transaction?.status == "paid") {
                    println("Cobrança realizada com sucesso! ID: ${transaction.tid}")
                } else {
                    println("Erro: ${transaction?.message}")
                }
            } else {
                println("Erro na requisição: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
            println("Falha na comunicação: ${t.message}")
        }
    })
}