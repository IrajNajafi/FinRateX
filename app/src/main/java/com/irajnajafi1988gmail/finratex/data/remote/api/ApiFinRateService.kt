package com.irajnajafi1988gmail.finratex.data.remote.api

import com.irajnajafi1988gmail.finratex.data.remote.model.finRate.FinRateResponseDto
import retrofit2.http.GET

interface ApiFinRateService {

    @GET("currencies")
    suspend fun getFinRate(): FinRateResponseDto
}