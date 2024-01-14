package com.joaquin.cryptotoday.data.remote.responses

import androidx.annotation.Keep

@Keep
data class LiveApiResponse(
    val success: Boolean,
    val terms: String,
    val privacy: String,
    val timestamp: Long,
    val target: String,
    val rates: Map<String, Double>
)