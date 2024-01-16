package com.joaquin.cryptotoday.domain.remote.repository

import com.joaquin.cryptotoday.data.remote.responses.ListApiResponse
import com.joaquin.cryptotoday.data.remote.responses.LiveApiResponse
import com.joaquin.cryptotoday.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun fetchLiveCoins(): Flow<Resource<LiveApiResponse>>

    suspend fun fetchListCoins(): Flow<Resource<ListApiResponse>>
}