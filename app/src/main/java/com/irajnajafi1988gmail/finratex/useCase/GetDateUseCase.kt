package com.irajnajafi1988gmail.finratex.useCase

import com.irajnajafi1988gmail.finratex.domain.repository.DateRepository
import javax.inject.Inject

class GetDateUseCase @Inject constructor(
    private val repository: DateRepository
) {
    operator fun invoke(short: Boolean = true) = repository.getDate()
}