package no.haakon.springdemo.controller.repository

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface TransferRepository: CrudRepository<TransactionDBModel, UUID> {
}