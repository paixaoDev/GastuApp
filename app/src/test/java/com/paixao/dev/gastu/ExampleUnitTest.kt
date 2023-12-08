package com.paixao.dev.gastu

import com.paixao.dev.gastu.extensions.removeCurrencySymbol
import com.paixao.dev.gastu.extensions.toCurrency
import com.paixao.dev.gastu.extensions.unMaskValueToBigDecimal
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Test
    fun `when pass a float convert to currency`() {
        val expected = "R$ 10,35"
        val value = 10.35f

        assertEquals(expected, value.toCurrency())
    }

    @Test
    fun `when pass string convert remove currency symbol`() {
        val expected = "140.30"
        val value = "R$ 140.30"

        assertEquals(expected, value.removeCurrencySymbol())
    }

    @Test
    fun `when pass string convert to currency`() {
        val expected = "R$ 140.030,10"
        val value = "R$ 140.030,10"

        assertEquals(expected, value.unMaskValueToBigDecimal().toCurrency())
    }
}