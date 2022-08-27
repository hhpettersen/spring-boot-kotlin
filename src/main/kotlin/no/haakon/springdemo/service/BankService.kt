package no.haakon.springdemo.service

import no.haakon.springdemo.controller.model.Bank
import no.haakon.springdemo.datasource.BankDataSource
import org.springframework.stereotype.Service

@Service
data class BankService(private val dataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()

    fun getBank(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber)

    fun addBank(bank: Bank): Bank = dataSource.addBank(bank)

    fun updateBank(bank: Bank): Bank = dataSource.updateBank(bank)

    fun deleteBank(accountNumber: String) = dataSource.deleteBank(accountNumber)
}