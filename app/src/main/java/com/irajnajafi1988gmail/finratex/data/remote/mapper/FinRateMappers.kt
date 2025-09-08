package com.irajnajafi1988gmail.finratex.data.remote.mapper

import com.irajnajafi1988gmail.finratex.data.remote.model.finRate.Cryptocurrency
import com.irajnajafi1988gmail.finratex.data.remote.model.finRate.Currency
import com.irajnajafi1988gmail.finratex.data.remote.model.finRate.FinRateResponseDto
import com.irajnajafi1988gmail.finratex.data.remote.model.finRate.FineRateDto
import com.irajnajafi1988gmail.finratex.data.remote.model.finRate.Gold
import com.irajnajafi1988gmail.finratex.domain.model.CryptoInfo
import com.irajnajafi1988gmail.finratex.domain.model.CurrencyInfo
import com.irajnajafi1988gmail.finratex.domain.model.FinRateInfo
import com.irajnajafi1988gmail.finratex.domain.model.GoldInfo
// Convert FineRateDto to FinRateInfo domain model
fun FineRateDto.toDomain(): FinRateInfo = FinRateInfo(
    golds = golds.map { it.toDomain() },
    currencies = currencies.map { it.toDomain() },
    cryptocurrencies = cryptocurrencies.map { it.toDomain() }
)

// Convert Gold DTO to GoldInfo domain model
fun Gold.toDomain(): GoldInfo = GoldInfo(
    name = name.orEmpty(),
    label = label.orEmpty(),
    price = price ?: 0L
)

// Convert Currency DTO to CurrencyInfo domain model
fun Currency.toDomain(): CurrencyInfo = CurrencyInfo(
    name = name.orEmpty(),
    label = label.orEmpty(),
    price = price ?: 0L,
    symbol = symbol.orEmpty()
)

// Convert Cryptocurrency DTO to CryptoInfo domain model
fun Cryptocurrency.toDomain(): CryptoInfo = CryptoInfo(
    name = name.orEmpty(),
    label = label.orEmpty(),
    price = price ?: 0L
)

// Convert FinRateResponseDto to FinRateInfo domain model
fun FinRateResponseDto.toDomain(): FinRateInfo = data.toDomain()
