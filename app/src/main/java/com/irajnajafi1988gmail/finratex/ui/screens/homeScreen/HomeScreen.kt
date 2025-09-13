package com.irajnajafi1988gmail.finratex.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.irajnajafi1988gmail.finratex.R
import com.irajnajafi1988gmail.finratex.domain.model.GoldInfo
import com.irajnajafi1988gmail.finratex.ui.components.common.priceListScreen.PriceListScreen
import com.irajnajafi1988gmail.finratex.ui.components.common.state.UiStateHandler
import com.irajnajafi1988gmail.finratex.ui.components.date.DateItem
import com.irajnajafi1988gmail.finratex.ui.components.header.TitleItem
import com.irajnajafi1988gmail.finratex.ui.components.priceTitle.PriceSection
import com.irajnajafi1988gmail.finratex.ui.components.priceTitle.PriceTitle
import com.irajnajafi1988gmail.finratex.ui.theme.AppColor
import com.irajnajafi1988gmail.finratex.ui.viewModel.FinRateViewModel
import com.irajnajafi1988gmail.finratex.ui.viewModel.TitleViewModel

/**
 * The main screen of the app, displaying date, currency/gold/crypto prices,
 * and allowing switching between different price sections.
 */
@Composable
fun HomeScreen() {
    // Injecting the ViewModel for financial rates
    val finRateViewModel: FinRateViewModel = hiltViewModel()
    val state by finRateViewModel.finRateState.collectAsState() // Collecting UI state

    // Injecting ViewModel for header items (dollar, gold, bitcoin, coin)
    val viewModelTile: TitleViewModel = hiltViewModel()
    var selectedSection by remember { mutableStateOf(PriceSection.CURRENCY) } // Track selected price section

    // Collecting state flows from TitleViewModel
    val dollarState = viewModelTile.dollarState.collectAsState()
    val goldState = viewModelTile.goldState.collectAsState()
    val coinState = viewModelTile.coinState.collectAsState()
    val bitcoinState = viewModelTile.bitcoinState.collectAsState()

    // Main column for the entire HomeScreen content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        AppColor.SemiTransparentBlack,
                        AppColor.DeepTransparentBlack,
                        AppColor.DarkGoldenBrown
                    )
                )
            )
            .padding(top = 16.dp)
    ) {

        // Display today's date card
        DateItem()
        Spacer(modifier = Modifier.height(16.dp))

        // Display the horizontal scrollable header (bitcoin, coin, gold, dollar)
        TitleItem(
            dollarState = dollarState,
            goldState = goldState,
            coinState = coinState,
            bitcoinState = bitcoinState,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Section title to switch between Currency / Gold / Bitcoin
        PriceTitle(
            selectedSection = selectedSection,
            onClickPriceGold = { selectedSection = PriceSection.GOLD },
            onClickPriceCurrency = { selectedSection = PriceSection.CURRENCY },
            onClickBitCoin = { selectedSection = PriceSection.BITCOIN }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Display list of items depending on selected section
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            UiStateHandler(
                state = state, // Handle loading, error, empty, or success states
                onRetry = { finRateViewModel.refreshDate() } // Retry action for errors
            ) { data ->
                // Render the correct list based on selected section
                when (selectedSection) {
                    PriceSection.GOLD -> {
                        PriceListScreen(
                            items = data.golds,
                            imageResProvider = { goldInfoImage(it) },
                            labelExtractor = { it.label },
                            priceExtractor = { it.price },
                            symbolExtractor = {"ریال"}



                        )
                    }

                    PriceSection.CURRENCY -> {
                        PriceListScreen(
                            items = data.currencies,
                            imageResProvider = { R.drawable.ic_money },
                            labelExtractor = { it.label },
                            priceExtractor = { it.price },
                            symbolExtractor = {"ریال"}

                        )
                    }

                    PriceSection.BITCOIN -> {
                        PriceListScreen(
                            items = data.cryptocurrencies,
                            imageResProvider = { R.drawable.cryptocurrency },
                            labelExtractor = { it.label },
                            priceExtractor = { it.price },
                            symbolExtractor = {"$"}


                        )
                    }
                }
            }
        }
    }
}

/**
 * Utility function to provide proper image for GoldInfo items
 * based on label content (e.g., full coin vs treasure coin)
 */
fun goldInfoImage(goldInfo: GoldInfo): Int {
    return if (goldInfo.label.contains("سکه بهار آزادی") ||
        goldInfo.label.contains("نیم سکه بهار آزادی")
    ) R.drawable.gold else R.drawable.gold_treasure
}
