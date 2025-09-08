package com.irajnajafi1988gmail.finratex.data.remote.model.finRate

import com.google.gson.annotations.SerializedName

data class FinRateResponseDto (
    @SerializedName("success") val success: Int,
    @SerializedName("message") val message: String,
    @SerializedName("last_update") val lastUpdate: String,
    @SerializedName("source") val source: String,
    @SerializedName("data") val data: FineRateDto
)
