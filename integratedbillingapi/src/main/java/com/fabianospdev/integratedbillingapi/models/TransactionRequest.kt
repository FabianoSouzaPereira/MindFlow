package com.fabianospdev.integratedbillingapi.models

data class TransactionRequest(
    val api_key: String,
    val amount: Int, // Valor em centavos (ex: R$ 100,00 = 10000)
    val card_number: String,
    val card_expiration_date: String,
    val card_cvv: String,
    val card_holder_name: String,
    val payment_method: String = "credit_card",
    val installments: Int = 1
)