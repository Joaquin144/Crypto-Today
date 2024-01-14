package com.joaquin.cryptotoday.data.remote.repository

import com.joaquin.cryptotoday.data.remote.responses.ListApiResponse
import com.joaquin.cryptotoday.data.remote.responses.LiveApiResponse
import com.joaquin.cryptotoday.domain.CoinRepository
import com.joaquin.cryptotoday.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoinRepositoryImpl : CoinRepository {
    override fun fetchLiveCoins() = flow {
        emit(Resource.Loading<LiveApiResponse>())

    }

    override fun fetchListCoins() = flow {
        emit(Resource.Loading<ListApiResponse>())
    }
}