package com.irajnajafi1988gmail.finratex.domain.model

// Represents the full financial rates information including gold, currencies, and cryptocurrencies
data class FinRateInfo(
    val golds: List<GoldInfo>,
    val currencies: List<CurrencyInfo>,
    val cryptocurrencies: List<CryptoInfo>
)

// Represents a gold item with its name, display label, and price
data class GoldInfo(
    val name: String,
    val label: String,
    val price: Long
)

// Represents a currency item with name, display label, price, and symbol
data class CurrencyInfo(
    val name: String,
    val label: String,
    val price: Long,
    val symbol: String
)

// Represents a cryptocurrency item with name, display label, and price
data class CryptoInfo(
    val name: String,
    val label: String,
    val price: Long
)
