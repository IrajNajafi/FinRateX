package com.irajnajafi1988gmail.finratex.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.finratex.domain.model.CryptoInfo
import com.irajnajafi1988gmail.finratex.domain.model.CurrencyInfo
import com.irajnajafi1988gmail.finratex.domain.model.GoldInfo
import com.irajnajafi1988gmail.finratex.ui.components.common.state.DateUiState
import com.irajnajafi1988gmail.finratex.ui.components.common.state.UiStateError
import com.irajnajafi1988gmail.finratex.useCase.GetBitcoinUseCase
import com.irajnajafi1988gmail.finratex.useCase.GetCoinUseCase
import com.irajnajafi1988gmail.finratex.useCase.GetDollarUseCase
import com.irajnajafi1988gmail.finratex.useCase.GetGoldUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TitleViewModel @Inject constructor(
    private val getDollarUseCase: GetDollarUseCase,
    private val getGoldUseCase: GetGoldUseCase,
    private val getCoinUseCase: GetCoinUseCase,
    private val getBitcoinUseCase: GetBitcoinUseCase
) : ViewModel() {

    // برای دلار
    private val _dollarState = MutableStateFlow<DateUiState<CurrencyInfo>>(DateUiState.Loading)
    val dollarState: StateFlow<DateUiState<CurrencyInfo>> = _dollarState

    // برای طلا
    private val _goldState = MutableStateFlow<DateUiState<GoldInfo>>(DateUiState.Loading)
    val goldState: StateFlow<DateUiState<GoldInfo>> = _goldState

    // برای سکه
    private val _coinState = MutableStateFlow<DateUiState<GoldInfo>>(DateUiState.Loading)
    val coinState: StateFlow<DateUiState<GoldInfo>> = _coinState

    // برای بیت‌کوین
    private val _bitcoinState = MutableStateFlow<DateUiState<CryptoInfo>>(DateUiState.Loading)
    val bitcoinState: StateFlow<DateUiState<CryptoInfo>> = _bitcoinState

    init {
        fetchDollar()
        fetchGoldTitle()
        fetchCoinTitle()
        fetchBitcoin()
    }

    private fun <T> collectData(
        useCase: Flow<T>,
        stateFlow: MutableStateFlow<DateUiState<T>>,
        errorMessage: String
    ) {
        viewModelScope.launch {
            useCase
                .onStart {
                    Log.d("TitleViewModel", " Loading started for $errorMessage")
                    stateFlow.value = DateUiState.Loading
                }
                .catch { throwable ->
                    Log.e("TitleViewModel", " Error in $errorMessage -> ${throwable.message}", throwable)
                    stateFlow.value = DateUiState.Error(
                        UiStateError.Unknown(Throwable(throwable.message ?: errorMessage))
                    )
                }
                .collect { data ->
                    Log.d("TitleViewModel", "Success for $errorMessage -> $data")
                    stateFlow.value = DateUiState.Success(data)
                }
        }
    }

    private fun fetchDollar() {
        collectData(
            useCase = getDollarUseCase(),
            stateFlow = _dollarState,
            errorMessage = "دریافت دلار"
        )
    }

    private fun fetchGoldTitle() {
        collectData(
            useCase = getGoldUseCase(),
            stateFlow = _goldState,
            errorMessage = "دریافت طلا"
        )
    }

    private fun fetchBitcoin() {
        collectData(
            useCase = getBitcoinUseCase(),
            stateFlow = _bitcoinState,
            errorMessage = "دریافت بیت‌کوین"
        )
    }

    private fun fetchCoinTitle() {
        collectData(
            useCase = getCoinUseCase(),
            stateFlow = _coinState,
            errorMessage = "دریافت سکه بهار آزادی"
        )
    }
}
