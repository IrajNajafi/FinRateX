package com.irajnajafi1988gmail.finratex.ui.components.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.irajnajafi1988gmail.finratex.R
import com.irajnajafi1988gmail.finratex.domain.model.CryptoInfo
import com.irajnajafi1988gmail.finratex.domain.model.CurrencyInfo
import com.irajnajafi1988gmail.finratex.domain.model.GoldInfo
import com.irajnajafi1988gmail.finratex.ui.components.common.state.DateUiState
import com.irajnajafi1988gmail.finratex.ui.components.common.state.UiStateHandler
import com.irajnajafi1988gmail.finratex.utils.formatRialToToman

/**
 * Horizontal list of cards showing prices for Dollar, Gold, Coin, and Bitcoin.
 * Each card reacts to its respective state using [UiStateHandler].
 */
@Composable
fun TitleItem(
    dollarState: State<DateUiState<CurrencyInfo>>,
    goldState: State<DateUiState<GoldInfo>>,
    coinState: State<DateUiState<GoldInfo>>,
    bitcoinState: State<DateUiState<CryptoInfo>>,
    modifier: Modifier = Modifier,
) {
    val items = listOf(
        Pair(R.drawable.cryptocurrency, bitcoinState),
        Pair(R.drawable.gold, coinState),
        Pair(R.drawable.gold_treasure, goldState),
        Pair(R.drawable.ic_money, dollarState)
    )

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Iterate through items and show each using TitleCard
        itemsIndexed(items) { _, (imageRes, state) ->
            UiStateHandler(state = state.value, onRetry = { }) { data ->
                TitleCard(imageRes = imageRes, data = data)
            }
        }
    }
}

/**
 * Generic card displaying an image, title, and price for a currency, gold, or crypto.
 *
 * @param imageRes Image resource ID for the item
 * @param data The data object, can be CurrencyInfo, GoldInfo, or CryptoInfo
 */
@Composable
fun <T> TitleCard(imageRes: Int, data: T) {
    val title: String
    val priceText: String

    // Extract title and formatted price based on data type
    when (data) {
        is CurrencyInfo -> {
            title = data.label
            priceText = formatRialToToman(data.price)
        }
        is GoldInfo -> {
            title = data.label
            priceText = formatRialToToman(data.price)
        }
        is CryptoInfo -> {
            title = data.label
            priceText = formatRialToToman(data.price)
        }
        else -> {
            title = ""
            priceText = ""
        }
    }

    // Card layout for the item
    Card(
        modifier = Modifier.size(180.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black.copy(0.7f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Item image
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Title label
            Text(
                text = title,
                color = Color.White,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Price row
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.toman), // Currency unit
                    color = Color.White,
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = priceText, // Formatted price
                    color = Color.White,
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}
