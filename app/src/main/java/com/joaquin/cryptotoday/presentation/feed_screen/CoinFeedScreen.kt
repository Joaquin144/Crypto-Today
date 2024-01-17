package com.joaquin.cryptotoday.presentation.feed_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.joaquin.cryptotoday.R
import com.joaquin.cryptotoday.domain.CoinFeedModel
import com.joaquin.cryptotoday.presentation.common.ErrorScreen
import com.joaquin.cryptotoday.presentation.common.LoadingScreen

@Composable
fun CoinFeedScreen() {
    val viewModel: CoinFeedViewModel = viewModel()
    val coinFeedState by viewModel.coinFeed.collectAsState(CoinFeedState(isLoading = true))

    CoinFeedContent(coinFeedState) { viewModel.fetchCoinsFeed() }
}

@Composable
fun CoinFeedContent(coinFeedState: CoinFeedState, refreshData: () -> Unit) {
    if (coinFeedState.isLoading) {
        LoadingScreen()
    } else if (coinFeedState.error.isNotEmpty()) {
        ErrorScreen(errorMessage = coinFeedState.error) {
            refreshData()
        }
    } else {
        CoinList(coinFeedState.lastUpdatedTime, coins = coinFeedState.feedCoins)
    }

}

@Composable
fun CoinList(timestamp: String, coins: List<CoinFeedModel>) {
    Column {
        Text(
            text = timestamp,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(items = coins) { coin ->
                CoinListItem(coin = coin)
            }
        }
    }
}

@Composable
fun CoinListItem(coin: CoinFeedModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = coin.iconUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(R.drawable.currency_bitcoin)
                    }).build()
            ),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Coin Details
        Column {
            Text(text = "Name: ${coin.fullName}", fontWeight = FontWeight.Bold)
            Text(text = "Exchange Rate: ${coin.exchangeRateRoundedOff}")
        }
    }
}