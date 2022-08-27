package no.haakon.springdemo.datasource.mock

import no.haakon.springdemo.controller.model.Bank
import no.haakon.springdemo.datasource.BankDataSource
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

    private val banks = mutableListOf(
        Bank(
            accountNumber = "1",
            trust = 10.0,
        ),
        Bank(
            accountNumber = "2",
            trust = 20.0,
        )
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank =
        banks.firstOrNull { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find bank with account number: $accountNumber")

    override fun addBank(bank: Bank): Bank {
        if (banks.none { it.accountNumber == bank.accountNumber }) {
            banks.add(bank)
        } else {
            throw IllegalArgumentException("bank with account number ${bank.accountNumber} already exists")
        }

        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val index = banks.indexOfFirst { it.accountNumber == bank.accountNumber }

        if (index != -1) {
            banks[index] = bank
        } else throw NoSuchElementException("Could not find bank with account number: $bank.accountNumber")

        return bank
    }

    override fun deleteBank(accountNumber: String) {
        val currentBank = banks.firstOrNull { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with account number: $accountNumber")

        banks.remove(currentBank)
    }
}
