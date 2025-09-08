package com.irajnajafi1988gmail.finratex.useCase

import com.irajnajafi1988gmail.finratex.domain.model.CryptoInfo
import com.irajnajafi1988gmail.finratex.domain.model.CurrencyInfo
import com.irajnajafi1988gmail.finratex.domain.model.GoldInfo
import com.irajnajafi1988gmail.finratex.domain.repository.FinRateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDollarUseCase @Inject constructor(
    private val repository: FinRateRepository
) {
    operator fun invoke(): Flow<CurrencyInfo> = repository.getDollar()
}

class GetGoldUseCase @Inject constructor(
    private val repository: FinRateRepository
) {
    operator fun invoke(): Flow<GoldInfo> = repository.getGold()
}

class GetCoinUseCase @Inject constructor(
    private val repository: FinRateRepository
) {
    operator fun invoke(): Flow<GoldInfo> = repository.getCoin()
}

class GetBitcoinUseCase @Inject constructor(
    private val repository: FinRateRepository
) {
    operator fun invoke(): Flow<CryptoInfo> = repository.getBitcoin()
}

