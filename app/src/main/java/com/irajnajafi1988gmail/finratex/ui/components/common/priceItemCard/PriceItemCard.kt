package com.irajnajafi1988gmail.finratex.ui.components.common.priceItemCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irajnajafi1988gmail.finratex.ui.theme.AppColor
import com.irajnajafi1988gmail.finratex.utils.formatRialToToman
import com.irajnajafi1988gmail.finratex.R


/**
 * A generic card to display price information for gold, currency, crypto, etc.
 *
 * @param label the name or label of the item
 * @param price the price value in Rial
 * @param imageResProvider lambda providing the resource ID for the item's image
 */
@Composable
fun PriceItemCard(
    label: String,
    price: Long,
    imageResProvider: () -> Int
) {
    // Row container for the entire card
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        AppColor.DarkGrayStart,
                        AppColor.DarkGrayEnd
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Currency unit (fixed text)
        Text(
            text = stringResource(R.string.toman),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Display price, formatted from Rial to Toman
        Text(
            text = formatRialToToman(price),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Display the label/name of the item
        Text(
            text = label,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        // Image card for the item
        Card(
            modifier = Modifier.size(70.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Image(
                painter = painterResource(imageResProvider()),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                AppColor.SemiTransparentBlack,
                                AppColor.DeepTransparentBlack,
                                AppColor.DarkGoldenBrown
                            )
                        )
                    )
                    .padding(8.dp)
            )
        }
    }
}
