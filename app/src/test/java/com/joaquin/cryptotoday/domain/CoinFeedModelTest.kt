package com.joaquin.cryptotoday.domain

import org.junit.Assert.*
import org.junit.Test

class CoinFeedModelTest {
    @Test
    fun `test exchangeRateRoundedOff with positive value`() {
        // Arrange
        val coin = CoinFeedModel("LOK", "LokalCoin", "fake-url.com", 0.123456789)

        // Act
        val roundedOff = coin.exchangeRateRoundedOff

        // Assert
        assertEquals("0.123457", roundedOff)
    }

    @Test
    fun `test exchangeRateRoundedOff with negative value`() {
        // Arrange
        val coin = CoinFeedModel("ETH", "Ethereum", "fake-url.two.com", -0.987654321)

        // Act
        val roundedOff = coin.exchangeRateRoundedOff

        // Assert
        assertEquals("-0.987654", roundedOff)
    }

    @Test
    fun `test exchangeRateRoundedOff with zero value`() {
        // Arrange
        val coin = CoinFeedModel("XRP", "Ripple", "fake-url.three.com", 0.0)

        // Act
        val roundedOff = coin.exchangeRateRoundedOff

        // Assert
        assertEquals("0.000000", roundedOff)
    }
}