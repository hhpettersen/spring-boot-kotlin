package no.haakon.springdemo.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {

    private val mockDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of banks`() {
        // given


        // when
        val banks = mockDataSource.retrieveBanks()

        // then
        assertThat(banks.size).isGreaterThanOrEqualTo(2)
    }

    @Test
    fun `should provide some mock data`() {
        // given


        // when
        val banks = mockDataSource.retrieveBanks()

        // then
        assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
    }
}