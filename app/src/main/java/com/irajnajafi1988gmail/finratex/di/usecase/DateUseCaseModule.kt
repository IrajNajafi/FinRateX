package com.irajnajafi1988gmail.finratex.di.usecase

import com.irajnajafi1988gmail.finratex.domain.repository.DateRepository
import com.irajnajafi1988gmail.finratex.useCase.GetDateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DateUseCaseModule {

    // Provide GetDateUseCase as a singleton using Hilt DI
    // Enables ViewModels to inject and use it without creating multiple instances
    @Provides
    @Singleton
    fun providerGetDateUseCase(repository: DateRepository) = GetDateUseCase(repository)
}
