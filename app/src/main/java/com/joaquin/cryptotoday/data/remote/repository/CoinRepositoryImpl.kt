package com.joaquin.cryptotoday.data.remote.repository

import com.joaquin.cryptotoday.BuildConfig
import com.joaquin.cryptotoday.data.remote.api.CryptoApi
import com.joaquin.cryptotoday.data.remote.responses.ListApiResponse
import com.joaquin.cryptotoday.data.remote.responses.LiveApiResponse
import com.joaquin.cryptotoday.domain.CoinRepository
import com.joaquin.cryptotoday.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CoinRepositoryImpl(private val cryptoApi: CryptoApi) : CoinRepository {
    private val DEFAULT_ERROR_MESSAGE = "Unknown Exception occurred"

    override fun fetchLiveCoins() = flow {
        emit(Resource.Loading<LiveApiResponse>())
        try {
            val liveRes = cryptoApi.fetchLiveCoins(BuildConfig.COIN_LAYER_API_KEY)
            val liveResBody = liveRes.body()
            if (liveRes.isSuccessful && liveResBody != null) {
                emit(Resource.Success<LiveApiResponse>(liveResBody))
            } else {
                emit(Resource.Error<LiveApiResponse>(liveRes.message()))
            }
        } catch (exception: Exception) {
            emit(Resource.Error<LiveApiResponse>(exception.message ?: DEFAULT_ERROR_MESSAGE))
        }
    }

    override fun fetchListCoins() = flow {
        emit(Resource.Loading<ListApiResponse>())
        try {
            val listRes = cryptoApi.fetchListCoins(BuildConfig.COIN_LAYER_API_KEY)
            val listResBody = listRes.body()
            if (listRes.isSuccessful && listResBody != null) {
                emit(Resource.Success<ListApiResponse>(listResBody))
            } else {
                emit(Resource.Error<ListApiResponse>(listRes.message()))
            }
        } catch (exception: Exception) {
            emit(Resource.Error<ListApiResponse>(exception.message ?: DEFAULT_ERROR_MESSAGE))
        }
    }
}