package com.irajnajafi1988gmail.finratex.ui.components.common.state

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.irajnajafi1988gmail.finratex.R
import com.irajnajafi1988gmail.finratex.ui.components.common.loading.StateLoading
import com.irajnajafi1988gmail.finratex.ui.components.common.stateError.StateError

/**
 * A generic UI state handler for date-related data.
 *
 * This composable renders different UI elements based on the current state:
 * - Loading: shows a loading animation
 * - Empty: shows a placeholder text indicating no data
 * - Success: invokes the onSuccess composable with the fetched data
 * - Error: shows an error state with retry option
 *
 * @param T the type of data being handled
 * @param state current UI state of type [DateUiState]
 * @param onRetry lambda to retry fetching data in case of error
 * @param onSuccess composable lambda invoked when data is successfully loaded
 */
@Composable
fun <T> UiStateHandler(
    state: DateUiState<T>,
    onRetry: () -> Unit,
    onSuccess: @Composable (T) -> Unit
) {
    when (state) {
        is DateUiState.Loading -> {
            // Show loading animation
            StateLoading()
        }

        is DateUiState.Empty -> {
            // Show placeholder text when no data is available
            Text(
                text = stringResource(id = R.string.No_data_available),
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        is DateUiState.Success -> {
            // Render content using provided composable
            onSuccess(state.data)
        }

        is DateUiState.Error -> {
            // Show error state with retry button
            StateError(onRetry)
        }
    }
}
