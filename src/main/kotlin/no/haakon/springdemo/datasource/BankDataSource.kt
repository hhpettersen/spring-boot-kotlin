package no.haakon.springdemo.datasource

import no.haakon.springdemo.controller.model.Bank

interface BankDataSource {

    fun retrieveBanks(): Collection<Bank>

    fun retrieveBank(accountNumber: String) : Bank

    fun addBank(bank: Bank): Bank

    fun updateBank(bank: Bank): Bank

    fun deleteBank(accountNumber: String)
}