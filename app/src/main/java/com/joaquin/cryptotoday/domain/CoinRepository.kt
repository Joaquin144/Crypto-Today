package com.joaquin.cryptotoday.domain

import com.joaquin.cryptotoday.data.remote.responses.ListApiResponse
import com.joaquin.cryptotoday.data.remote.responses.LiveApiResponse
import com.joaquin.cryptotoday.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    fun fetchLiveCoins(): Flow<Resource<LiveApiResponse>>

    fun fetchListCoins(): Flow<Resource<ListApiResponse>>
}