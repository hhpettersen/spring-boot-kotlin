package no.haakon.springdemo.controller

import no.haakon.springdemo.controller.model.OverviewTransactionModel
import no.haakon.springdemo.controller.model.TransactionModel
import no.haakon.springdemo.controller.model.convertToDBModel
import no.haakon.springdemo.controller.model.convertToOverviewTransactionModel
import no.haakon.springdemo.controller.repository.TransferRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transfer")
class TransferController(val repository: TransferRepository) {

    @PostMapping("/new")
    fun newTransfer(@RequestBody transactionModel: TransactionModel) {
        repository.save(transactionModel.convertToDBModel())
    }

    @GetMapping("/all")
    fun getAllTransfers(): List<OverviewTransactionModel> {
        return repository.findAll().map { it.convertToOverviewTransactionModel() }
    }
}
