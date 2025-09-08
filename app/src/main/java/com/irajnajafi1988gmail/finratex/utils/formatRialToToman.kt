package com.irajnajafi1988gmail.finratex.utils

import java.text.DecimalFormat

fun formatRialToToman(price: Long): String {
    val priceInToman = price / 10
    val formatter = DecimalFormat("#,###")
    return formatter.format(priceInToman)
}