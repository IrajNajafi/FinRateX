package com.irajnajafi1988gmail.finratex.domain.repository

import com.irajnajafi1988gmail.finratex.domain.model.DateInfo
import kotlinx.coroutines.flow.Flow

// Repository interface to fetch date information from remote or local source
interface DateRepository {

    /**
     * Returns a Flow emitting DateInfo.
     *
     * @param short If true, returns a shortened date format; otherwise, full date details.
     */
    fun getDate(short: Boolean = true): Flow<DateInfo>
}
