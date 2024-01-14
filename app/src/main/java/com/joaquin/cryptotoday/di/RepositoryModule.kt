package com.joaquin.cryptotoday.di

import com.joaquin.cryptotoday.data.remote.api.CryptoApi
import com.joaquin.cryptotoday.data.remote.repository.CoinRepositoryImpl
import com.joaquin.cryptotoday.domain.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCoinRepositoryImpl(cryptoApi: CryptoApi): CoinRepository {
        return CoinRepositoryImpl(cryptoApi = cryptoApi)
    }
}