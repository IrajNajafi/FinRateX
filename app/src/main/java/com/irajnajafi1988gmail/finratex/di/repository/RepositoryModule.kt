package com.irajnajafi1988gmail.finratex.di.repository

import com.irajnajafi1988gmail.finratex.data.remote.repository.DateRepositoryImpl
import com.irajnajafi1988gmail.finratex.data.remote.repository.FinRateRepositoryImpl
import com.irajnajafi1988gmail.finratex.domain.repository.DateRepository
import com.irajnajafi1988gmail.finratex.domain.repository.FinRateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // Bind DateRepositoryImpl to DateRepository interface
    @Binds
    @Singleton
    abstract fun bindDateRepository(
        impl: DateRepositoryImpl
    ): DateRepository

    // Bind FinRateRepositoryImpl to FinRateRepository interface
    @Binds
    @Singleton
    abstract fun bindFinRateRepository(
        impl: FinRateRepositoryImpl
    ): FinRateRepository
}
