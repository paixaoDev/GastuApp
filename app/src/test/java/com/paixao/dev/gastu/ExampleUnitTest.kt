package com.paixao.dev.gastu

import com.paixao.dev.gastu.extensions.removeCurrency
import com.paixao.dev.gastu.extensions.toCurrency
import com.paixao.dev.gastu.extensions.toFloatCurrency
import org.junit.Test
import org.junit.Assert.*


class ExampleUnitTest {

    @Test
    fun `when pass a float convert to currency`() {
        val expected = "R$ 10,35"
        val value = 10.35f

        assertEquals(expected, value.toCurrency())
    }

    @Test
    fun `when pass string convert to float`() {
        val expected = 140.30f
        val value = "R$ 140.30"

        assertEquals(expected, value.toFloatCurrency())
    }

    @Test
    fun `when pass string convert to float 2`() {
        val expected = "140.30"
        val value = "R$ 140.30,00"

        assertEquals(expected, value.removeCurrency())
    }
}