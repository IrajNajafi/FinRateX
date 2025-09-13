package com.irajnajafi1988gmail.finratex.ui.components.common.priceListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.irajnajafi1988gmail.finratex.ui.components.common.priceItemCard.PriceItemCard

@Composable
fun <T> PriceListScreen(
    items: List<T>,                      // List of generic items to display
    imageResProvider: (T) -> Int,        // Lambda to provide image resource for each item
    labelExtractor: (T) -> String,       // Lambda to extract the label/name of each item
    priceExtractor: (T) -> Long, // Lambda to extract the price of each item
    symbolExtractor:(T)->String

) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp) // Space between items
    ) {
        items(items) { item ->
            // Display each item in a card using PriceItemCard

                PriceItemCard(
                    label = labelExtractor(item),
                    price = priceExtractor(item),
                    imageResProvider = { imageResProvider(item) },
                    symbol = symbolExtractor(item)



                )
            }
        }
    }

