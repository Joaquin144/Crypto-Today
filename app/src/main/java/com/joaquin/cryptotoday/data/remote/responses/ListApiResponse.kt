package com.joaquin.cryptotoday.data.remote.responses

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ListApiResponse(
    val success: Boolean,
    val crypto: Map<String, CryptoListModel>,
    val fiat: Map<String, String>
)

@Keep
data class CryptoListModel(
    val symbol: String,
    val name: String,
    @SerializedName("name_full")
    val fullName: String,
    @SerializedName("max_supply")
    val maxSupply: String,
    @SerializedName("icon_url")
    val iconUrl: String
)