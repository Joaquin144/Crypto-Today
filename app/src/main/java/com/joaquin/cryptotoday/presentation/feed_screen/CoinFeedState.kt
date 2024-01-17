package com.joaquin.cryptotoday.presentation.feed_screen

import com.joaquin.cryptotoday.data.remote.responses.LiveApiResponse
import com.joaquin.cryptotoday.domain.CoinFeedModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class CoinFeedState(
    val isLoading: Boolean = false,
    val feedCoins: List<CoinFeedModel> = emptyList(),
    val error: String = ""
) {
    var lastUpdatedTime: String = ""
        private set

    //Using init block to set the timestamp
    init {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault())
        lastUpdatedTime = "Last Updated On ${sdf.format(Date())}"
    }
}