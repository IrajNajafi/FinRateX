package com.irajnajafi1988gmail.finratex.utils

import java.text.DecimalFormat

fun formatWithCommas(number: Long): String {
    // از DecimalFormat برای اضافه کردن جداکننده هزارگان استفاده می‌کنیم
    val formatter = DecimalFormat("#,###")
    return formatter.format(number)
}
