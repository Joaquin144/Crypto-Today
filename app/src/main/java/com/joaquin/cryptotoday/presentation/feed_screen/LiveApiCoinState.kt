package com.joaquin.cryptotoday.presentation.feed_screen

import com.joaquin.cryptotoday.data.remote.responses.LiveApiResponse

data class LiveApiCoinState(
    val isLoading: Boolean = false,
    val liveCoins: LiveApiResponse? = null,
    val error: String = ""
)
