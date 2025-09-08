package com.irajnajafi1988gmail.finratex.di.usecase

import com.irajnajafi1988gmail.finratex.domain.repository.FinRateRepository
import com.irajnajafi1988gmail.finratex.useCase.GetBitcoinUseCase
import com.irajnajafi1988gmail.finratex.useCase.GetCoinUseCase
import com.irajnajafi1988gmail.finratex.useCase.GetDollarUseCase
import com.irajnajafi1988gmail.finratex.useCase.GetGoldUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TitleUseCaseModule {

    // Provide GetDollarUseCase as a singleton for DI
    // Can be injected into ViewModels for fetching Dollar data
    @Provides
    @Singleton
    fun provideGetDollarUseCase(repository: FinRateRepository): GetDollarUseCase {
        return GetDollarUseCase(repository)
    }

    // Provide GetGoldUseCase as a singleton for DI
    // Can be injected into ViewModels for fetching Gold data
    @Provides
    @Singleton
    fun provideGetGoldUseCase(repository: FinRateRepository): GetGoldUseCase {
        return GetGoldUseCase(repository)
    }

    // Provide GetBitcoinUseCase as a singleton for DI
    // Can be injected into ViewModels for fetching Bitcoin data
    @Provides
    @Singleton
    fun provideGetBitcoinUseCase(repository: FinRateRepository): GetBitcoinUseCase {
        return GetBitcoinUseCase(repository)
    }

    // Provide GetCoinUseCase as a singleton for DI
    // Can be injected into ViewModels for fetching Coin data
    @Provides
    @Singleton
    fun provideGetCoinUseCase(repository: FinRateRepository): GetCoinUseCase {
        return GetCoinUseCase(repository)
    }
}
