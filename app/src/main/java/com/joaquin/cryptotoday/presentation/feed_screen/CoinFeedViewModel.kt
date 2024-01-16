package com.joaquin.cryptotoday.presentation.feed_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaquin.cryptotoday.data.remote.repository.DEFAULT_ERROR_MESSAGE
import com.joaquin.cryptotoday.data.remote.responses.ListApiResponse
import com.joaquin.cryptotoday.data.remote.responses.LiveApiResponse
import com.joaquin.cryptotoday.domain.CoinFeedModel
import com.joaquin.cryptotoday.domain.remote.repository.CoinRepository
import com.joaquin.cryptotoday.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinFeedViewModel @Inject constructor(private val repository: CoinRepository) : ViewModel() {

    private val _liveCoinsFlow = MutableStateFlow<LiveApiCoinState?>(null)
    private val _listCoinsFlow = MutableStateFlow<ListApiCoinState?>(null)
    private var feedFetchingJob: Job? = null

    //Only This flow would get collected by UI
    val coinFeed: Flow<CoinFeedState> = combine(
        _liveCoinsFlow,
        _listCoinsFlow
    ) { live, list ->
        mergeLiveAndListStateFlows(live, list)
    }

    init {
        fetchCoinsFeed()
    }

    fun fetchCoinsFeed() {
        feedFetchingJob?.cancel()//Clear any previous job pending
        feedFetchingJob = viewModelScope.launch() {
            repository.fetchLiveCoins().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _liveCoinsFlow.value = LiveApiCoinState(liveCoins = result.data)
                    }

                    is Resource.Error -> {
                        _liveCoinsFlow.value =
                            LiveApiCoinState(error = result.message ?: DEFAULT_ERROR_MESSAGE)
                    }

                    is Resource.Loading -> {
                        _liveCoinsFlow.value = LiveApiCoinState(isLoading = true)
                    }
                }
            }

            repository.fetchListCoins().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _listCoinsFlow.value = ListApiCoinState(listCoins = result.data)
                    }

                    is Resource.Error -> {
                        _listCoinsFlow.value =
                            ListApiCoinState(error = result.message ?: DEFAULT_ERROR_MESSAGE)
                    }

                    is Resource.Loading -> {
                        _listCoinsFlow.value = ListApiCoinState(isLoading = true)
                    }
                }
            }
        }
    }

    //Logic to merge 2 StateFlows
    private fun mergeLiveAndListStateFlows(
        live: LiveApiCoinState?,
        list: ListApiCoinState?
    ): CoinFeedState {
        if (live != null && list != null) {
            //Check for any errors
            if (live.error.isNotEmpty()) {
                return CoinFeedState(error = live.error)
            } else if (list.error.isNotEmpty()) {
                return CoinFeedState(error = list.error)
            }

            //Check for loading
            if (live.isLoading || list.isLoading) {
                return CoinFeedState(isLoading = true)
            }

            //Both APIs are done
            val feedList: List<CoinFeedModel> =
                mergeLiveAndListResponses(live.liveCoins, list.listCoins)
            return CoinFeedState(feedCoins = feedList)
        } else {
            //Show error from list or live
            return CoinFeedState(error = live?.error ?: list?.error ?: DEFAULT_ERROR_MESSAGE)
        }
    }

    //Logic to merge 2 different Responses
    private fun mergeLiveAndListResponses(
        liveCoins: LiveApiResponse?,
        listCoins: ListApiResponse?
    ): List<CoinFeedModel> {
        if (liveCoins != null && listCoins != null) {
            val commonSymbols = listCoins.crypto.keys.intersect(liveCoins.rates.keys)

            return commonSymbols.map { symbol ->
                val cryptoModel = listCoins.crypto[symbol]
                val exchangeRate = liveCoins.rates[symbol]

                CoinFeedModel(
                    symbol = symbol,
                    fullName = cryptoModel?.fullName ?: "",
                    iconUrl = cryptoModel?.iconUrl ?: "",
                    exchangeRate = exchangeRate ?: 0.0
                )
            }
        } else {
            return emptyList()
        }
    }
}
