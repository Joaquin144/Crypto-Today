package com.joaquin.cryptotoday.data.remote.api

import com.joaquin.cryptotoday.data.remote.responses.ListApiResponse
import com.joaquin.cryptotoday.data.remote.responses.LiveApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {
    @GET("live")
    suspend fun fetchLiveCoins(@Query("access_key") apiKey: String): Response<LiveApiResponse>

    @GET("list")
    suspend fun fetchListCoins(@Query("access_key") apiKey: String): Response<ListApiResponse>
}