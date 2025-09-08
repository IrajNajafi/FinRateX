package com.irajnajafi1988gmail.finratex.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.finratex.domain.model.FinRateInfo
import com.irajnajafi1988gmail.finratex.ui.components.common.state.DateUiState
import com.irajnajafi1988gmail.finratex.ui.components.common.state.UiStateError
import com.irajnajafi1988gmail.finratex.useCase.GetFinRateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinRateViewModel @Inject constructor(
    private val getFinRateUseCase: GetFinRateUseCase
) : ViewModel() {

    private val TAG = "FinRateViewModel"

    private val _finRateState = MutableStateFlow<DateUiState<FinRateInfo>>(DateUiState.Loading)
    val finRateState: StateFlow<DateUiState<FinRateInfo>> = _finRateState

    init {
       refreshDate()
    }

    private fun fetchFinRate() {
        viewModelScope.launch {
            getFinRateUseCase()
                .onStart {
                    Log.d(TAG, "شروع دریافت نرخ‌ها...")
                    _finRateState.value = DateUiState.Loading
                }
                .catch { throwable ->
                    Log.e(TAG, "خطا در دریافت نرخ‌ها: ${throwable.message}", throwable)
                    _finRateState.value = DateUiState.Error(UiStateError.Unknown(throwable))
                }
                .collect { data ->
                    Log.d(TAG, "نرخ‌ها با موفقیت دریافت شدند: $data")
                    _finRateState.value = DateUiState.Success(data)
                }
        }
    }

    fun refreshDate() {
        fetchFinRate()
    }
}
