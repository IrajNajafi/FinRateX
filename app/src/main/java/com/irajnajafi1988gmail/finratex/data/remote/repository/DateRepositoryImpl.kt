package com.irajnajafi1988gmail.finratex.data.remote.repository

import com.irajnajafi1988gmail.finratex.data.remote.api.ApiDateService
import com.irajnajafi1988gmail.finratex.data.remote.mapper.toDomain
import com.irajnajafi1988gmail.finratex.domain.model.DateInfo
import com.irajnajafi1988gmail.finratex.domain.repository.DateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
/**
 * Implementation of DateRepository
 * Fetches date from remote API and maps it to domain model
 */
@Singleton
class DateRepositoryImpl @Inject constructor(
    private val dateApi: ApiDateService
) : DateRepository {

    /**
     * Get current date from API
     * @param short whether to get short format
     * @return Flow emitting DateInfo
     */
    override fun getDate(short: Boolean): Flow<DateInfo> = flow {
        try {
            val response = dateApi.getDate(short)
            emit(response.toDomain()) // map DTO to domain model
        } catch (e: IOException) {
            throw e // network error
        } catch (e: Exception) {
            throw e // unknown error
        }
    }
}
