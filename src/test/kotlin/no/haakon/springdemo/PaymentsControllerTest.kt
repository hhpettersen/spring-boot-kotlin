package no.haakon.springdemo

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import no.haakon.springdemo.controller.model.TransactionModel
import no.haakon.springdemo.controller.repository.TransactionDBModel
import no.haakon.springdemo.controller.repository.TransferRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@WebMvcTest
class PaymentsControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var repository: TransferRepository

    @Test
    fun `should submit transaction with success`() {
        val transfer = TransactionModel(
            targetAccount = "hhp1337",
            description = "Food",
            amount = 10.50
        )

        every { repository.save(any()) } returns mockk()

        mockMvc.perform(post("/transfer/new")
            .content(ObjectMapper().writeValueAsString(transfer))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
    }

    @Test
    fun `should get transaction with success`() {
        val transaction = mockk<TransactionDBModel>().apply {
            every { amount } returns 10.50
            every { description } returns "Food"
            every { accountIdentifier } returns "hhp1337"
            every { date } returns Date()
            every { id } returns UUID.randomUUID()
        }

        every { repository.findAll() } returns listOf(transaction)

        mockMvc.perform(get("/transfer/all").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].description").value("Food"))
    }
}
