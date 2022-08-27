package no.haakon.springdemo.controller.model

import no.haakon.springdemo.controller.repository.TransactionDBModel

class TransactionModel(
    val targetAccount: String,
    val amount: Double,
    val description: String = "",
)

fun TransactionModel.convertToDBModel() = TransactionDBModel(
    accountIdentifier = this.targetAccount,
    amount = this.amount,
    description = this.description
)