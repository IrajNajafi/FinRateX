package com.irajnajafi1988gmail.finratex.data.remote.repository

import com.irajnajafi1988gmail.finratex.data.remote.api.ApiFinRateService
import com.irajnajafi1988gmail.finratex.data.remote.mapper.toDomain
import com.irajnajafi1988gmail.finratex.domain.model.CryptoInfo
import com.irajnajafi1988gmail.finratex.domain.model.CurrencyInfo
import com.irajnajafi1988gmail.finratex.domain.model.FinRateInfo
import com.irajnajafi1988gmail.finratex.domain.model.GoldInfo
import com.irajnajafi1988gmail.finratex.domain.repository.FinRateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of FinRateRepository
 * Fetches financial rates (gold, currency, crypto) from remote API
 * and caches the result to minimize network calls
 */
@Singleton
class FinRateRepositoryImpl @Inject constructor(
    private val api: ApiFinRateService
) : FinRateRepository {

    private var cachedFinRate: FinRateInfo? = null

    /**
     * Fetch all financial rates or return cached data if available
     */
    override fun getFinRate(): Flow<FinRateInfo> = flow {
        cachedFinRate?.let {
            emit(it) // return cached data
        } ?: run {
            val response = api.getFinRate()
            val finRateInfo = response.toDomain()
            cachedFinRate = finRateInfo
            emit(finRateInfo)
        }
    }

    /**
     * Generic helper to find an item in a FinRateInfo list
     */
    private fun <T> Flow<FinRateInfo>.findItem(
        itemsSelector: (FinRateInfo) -> List<T>,
        predicate: (T) -> Boolean,
        notFoundMessage: String
    ): Flow<T> = flow {
        collect { info ->
            val item = itemsSelector(info).find(predicate)
            if (item != null) emit(item)
            else throw Exception(notFoundMessage)
        }
    }

    /** Get USD currency info */
    override fun getDollar(): Flow<CurrencyInfo> =
        getFinRate().findItem(
            { it.currencies },
            { it.name.lowercase() == "dollar" },
            "Dollar not found"
        )

    /** Get coin info (e.g., Bahar Azadi) */
    override fun getCoin(): Flow<GoldInfo> =
        getFinRate().findItem(
            { it.golds },
            { it.name.lowercase() == "coin" },
            "Coin not found"
        )

    /** Get 18k gold info */
    override fun getGold(): Flow<GoldInfo> =
        getFinRate().findItem(
            { it.golds },
            { it.name.lowercase() == "gold_18" },
            "18k Gold not found"
        )

    /** Get Bitcoin info */
    override fun getBitcoin(): Flow<CryptoInfo> =
        getFinRate().findItem(
            { it.cryptocurrencies },
            { it.name.lowercase() == "bitcoin" },
            "Bitcoin not found"
        )
}
