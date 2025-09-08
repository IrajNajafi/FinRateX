package com.irajnajafi1988gmail.finratex.ui.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irajnajafi1988gmail.finratex.domain.model.DateInfo
import com.irajnajafi1988gmail.finratex.ui.components.common.state.DateUiState
import com.irajnajafi1988gmail.finratex.ui.components.common.state.UiStateError
import com.irajnajafi1988gmail.finratex.useCase.GetDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

/**
 * ViewModel responsible for fetching and managing the current date information.
 * It exposes a StateFlow<DateUiState<DateInfo>> for the UI to observe.
 */
@HiltViewModel
class DateViewModel @Inject constructor(
    private val getDateUseCase: GetDateUseCase
) : ViewModel() {

    // Internal mutable state flow to manage the date UI state
    private val _dateState = MutableStateFlow<DateUiState<DateInfo>>(DateUiState.Loading)

    // Public immutable state flow for UI observation
    val dateState: StateFlow<DateUiState<DateInfo>> = _dateState

    init {
        // Small delay before initial fetch to ensure UI is ready
        viewModelScope.launch {
            delay(500)
            refreshDate()
        }
    }

    /**
     * Fetch the date information from use case and update the UI state accordingly.
     */
    private fun fetchDate() {
        viewModelScope.launch {
            getDateUseCase()
                .onStart {
                    // Show loading state while fetching
                    _dateState.value = DateUiState.Loading
                }
                .catch { throwable ->
                    // Handle exceptions and map them to UiError
                    val error = when (throwable) {
                        is IOException -> UiStateError.NoInternet
                        else -> UiStateError.Unknown(throwable)
                    }
                    _dateState.value = DateUiState.Error(error)
                }
                .collect { data ->
                    // Check if the returned data is empty
                    if (data.year.isEmpty() || data.monthName.isEmpty() || data.dayOfMonth.isEmpty()) {
                        _dateState.value = DateUiState.Empty
                    } else {
                        // Successfully fetched data
                        _dateState.value = DateUiState.Success(data)
                    }
                }
        }
    }

    /**
     * Public method to refresh date manually.
     */
    fun refreshDate() {
        fetchDate()
    }

    /**
     * Helper function to format DateInfo into a readable string.
     * Example output: "Monday 7 September 2025"
     */
    @SuppressLint("DefaultLocale")
    fun getFormattedDate(date: DateInfo): String {
        return "${date.weekDayName} ${date.dayOfMonth} ${date.monthName} ${date.year}"
    }
}
