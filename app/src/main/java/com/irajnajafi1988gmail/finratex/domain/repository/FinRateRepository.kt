package com.irajnajafi1988gmail.finratex.domain.repository

import com.irajnajafi1988gmail.finratex.domain.model.CryptoInfo
import com.irajnajafi1988gmail.finratex.domain.model.CurrencyInfo
import com.irajnajafi1988gmail.finratex.domain.model.FinRateInfo
import com.irajnajafi1988gmail.finratex.domain.model.GoldInfo
import kotlinx.coroutines.flow.Flow

// Repository interface to fetch financial rates (gold, currency, crypto)
interface FinRateRepository {

    /**
     * Returns a Flow emitting the full financial rates information.
     */
    fun getFinRate(): Flow<FinRateInfo>

    /**
     * Returns a Flow emitting Dollar currency info.
     */
    fun getDollar(): Flow<CurrencyInfo>

    /**
     * Returns a Flow emitting a specific gold coin info.
     */
    fun getCoin(): Flow<GoldInfo>

    /**
     * Returns a Flow emitting 18-karat gold info.
     */
    fun getGold(): Flow<GoldInfo>

    /**
     * Returns a Flow emitting Bitcoin cryptocurrency info.
     */
    fun getBitcoin(): Flow<CryptoInfo>
}
