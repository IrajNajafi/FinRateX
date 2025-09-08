package com.irajnajafi1988gmail.finratex.ui.components.common.state

/**
 * Represents the different UI states for date-related data.
 *
 * @param T the type of data being represented in the Success state
 */
sealed class DateUiState<out T> {

    // State when data is being loaded
    data object Loading : DateUiState<Nothing>()

    // State when there is no data available
    data object Empty : DateUiState<Nothing>()

    // State when data is successfully fetched
    data class Success<T>(val data: T) : DateUiState<T>()

    // State when an error occurred while fetching data
    data class Error(val error: UiStateError) : DateUiState<Nothing>()
}
