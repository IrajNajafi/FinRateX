package com.irajnajafi1988gmail.finratex.data.remote.model.finRate

import com.google.gson.annotations.SerializedName

data class FineRateDto(
    @SerializedName("golds") val golds: List<Gold>,
    @SerializedName("currencies") val currencies: List<Currency>,
    @SerializedName("cryptocurrencies") val cryptocurrencies: List<Cryptocurrency>
)

data class Gold(
    @SerializedName("name") val name: String? = null,
    @SerializedName("label") val label: String? = null,
    @SerializedName("price") val price: Long? = null
)

data class Currency(
    @SerializedName("name") val name: String? = null,
    @SerializedName("label") val label: String? = null,
    @SerializedName("price") val price: Long? = null,
    @SerializedName("symbol") val symbol: String? = null
)

data class Cryptocurrency(
    @SerializedName("name") val name: String? = null,
    @SerializedName("label") val label: String? = null,
    @SerializedName("price") val price: Long? = null
)
