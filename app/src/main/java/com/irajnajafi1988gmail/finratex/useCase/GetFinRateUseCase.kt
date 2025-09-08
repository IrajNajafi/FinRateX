package com.irajnajafi1988gmail.finratex.useCase

import com.irajnajafi1988gmail.finratex.domain.model.FinRateInfo
import com.irajnajafi1988gmail.finratex.domain.repository.FinRateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFinRateUseCase @Inject constructor(
    private val repository: FinRateRepository
) {
    operator fun invoke(): Flow<FinRateInfo> = repository.getFinRate()
}