package com.irajnajafi1988gmail.finratex.ui.components.priceTitle

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Top section with selectable tabs for different price categories: Bitcoin, Currency, Gold.
 *
 * @param selectedSection Currently selected section
 * @param onClickPriceCurrency Callback when the currency tab is clicked
 * @param onClickPriceGold Callback when the gold tab is clicked
 * @param onClickBitCoin Callback when the bitcoin tab is clicked
 */
@Composable
fun PriceTitle(
    selectedSection: PriceSection,
    onClickPriceCurrency: () -> Unit,
    onClickPriceGold: () -> Unit,
    onClickBitCoin: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Bitcoin tab
            PriceItem(
                text = "ارز دیجیتال",
                onClick = { onClickBitCoin() },
                isSelected = selectedSection == PriceSection.BITCOIN,
                modifier = Modifier.weight(1f)
            )
            DividerItem()

            // Currency tab
            PriceItem(
                text = "ارز",
                onClick = { onClickPriceCurrency() },
                isSelected = selectedSection == PriceSection.CURRENCY,
                modifier = Modifier.weight(1f)
            )
            DividerItem()

            // Gold tab
            PriceItem(
                text = "طلا",
                onClick = { onClickPriceGold() },
                isSelected = selectedSection == PriceSection.GOLD,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

/**
 * Single item in the PriceTitle tab row.
 *
 * @param isSelected Whether this tab is currently selected
 * @param text Display text for the tab
 * @param onClick Callback when the tab is clicked
 * @param modifier Modifier for the tab
 */
@Composable
fun PriceItem(
    isSelected: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clickable { onClick() }
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tab text with color change when selected
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) Color(0xFFF1C40F) else Color.White
        )
    }
}

/**
 * Vertical divider between tabs.
 */
@Composable
private fun DividerItem() {
    Spacer(
        modifier = Modifier
            .width(1.dp)
            .height(60.dp)
            .background(Color.Gray)
    )
}
