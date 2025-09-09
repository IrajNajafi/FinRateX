package com.irajnajafi1988gmail.finratex.ui.components.date

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.irajnajafi1988gmail.finratex.ui.components.common.state.UiStateHandler
import com.irajnajafi1988gmail.finratex.ui.theme.AppColor
import com.irajnajafi1988gmail.finratex.ui.viewModel.DateViewModel

/**
 * Displays the current date card using data from [DateViewModel].
 * It reacts to loading, error, and empty states using [UiStateHandler].
 */
@Composable
fun DateItem(
    viewModel: DateViewModel = hiltViewModel()
) {
    val dateState by viewModel.dateState.collectAsState()

    // Handle different UI states (loading, error, success)
    UiStateHandler(
        state = dateState,
        onRetry = { viewModel.refreshDate() }
    ) { dateInfo ->
        // Display formatted date when data is available
        ItemDateTitle(formattedDate = viewModel.getFormattedDate(dateInfo))
    }
}

/**
 * Displays a styled card showing today's label and the formatted date.
 *
 * @param formattedDate The date string to display in the card.
 */
@Composable
fun ItemDateTitle(formattedDate: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 40.dp, vertical = 20.dp)

            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            AppColor.LightGoldenYellow,
                            AppColor.DarkGoldenYellow,
                            AppColor.SilverGray
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Label "Today"
                Text(
                    text = "امروز",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Formatted date text
                Text(
                    text = formattedDate,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
