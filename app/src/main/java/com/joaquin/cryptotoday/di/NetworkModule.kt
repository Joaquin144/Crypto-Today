package com.joaquin.cryptotoday.di

import android.util.Log
import com.google.gson.Gson
import com.joaquin.cryptotoday.BuildConfig
import com.joaquin.cryptotoday.data.remote.api.CryptoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideCryptoApi(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): CryptoApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
        return retrofit.create(CryptoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor() {
                Log.v("provideOkHttpClient", it)
            }
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
            client.addInterceptor(loggingInterceptor)
        }
        return client.build()
    }
}