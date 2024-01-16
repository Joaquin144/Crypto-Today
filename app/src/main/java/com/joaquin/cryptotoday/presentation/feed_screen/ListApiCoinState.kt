package com.joaquin.cryptotoday.presentation.feed_screen

import com.joaquin.cryptotoday.data.remote.responses.ListApiResponse

data class ListApiCoinState(
    val isLoading: Boolean = false,
    val listCoins: ListApiResponse? = null,
    val error: String = ""
)
