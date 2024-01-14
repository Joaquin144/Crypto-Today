package com.joaquin.cryptotoday.domain

data class CoinFeedModel(
    val fullName: String,
    val iconUrl: String,
    val exchangeRate: Double,
)