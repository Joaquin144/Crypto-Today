package com.joaquin.cryptotoday.domain

data class CoinFeedModel(
    val symbol: String,
    val fullName: String,
    val iconUrl: String,
    val exchangeRate: Double
) {
    val exchangeRateRoundedOff = String.format("%.6f", exchangeRate)
}