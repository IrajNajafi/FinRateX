package com.irajnajafi1988gmail.finratex.data.remote.api

import com.irajnajafi1988gmail.finratex.data.remote.model.date.DateResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDateService {
    @GET("date/now")
    suspend fun getDate(
        @Query("short") short: Boolean
    ): DateResponseDto
}