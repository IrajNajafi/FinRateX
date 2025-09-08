package com.irajnajafi1988gmail.finratex.di.usecase

import com.irajnajafi1988gmail.finratex.domain.repository.FinRateRepository
import com.irajnajafi1988gmail.finratex.useCase.GetFinRateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FinRateUseCaseModule {

    // Provide GetFinRateUseCase as a singleton using Hilt DI
    // Allows ViewModels or other consumers to inject and use the use case
    // without manually instantiating it multiple times
    @Provides
    @Singleton
    fun providerGetFinRatUseCase(repository: FinRateRepository) = GetFinRateUseCase(repository)
}
