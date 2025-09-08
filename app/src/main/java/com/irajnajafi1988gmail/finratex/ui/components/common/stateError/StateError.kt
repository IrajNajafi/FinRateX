package com.irajnajafi1988gmail.finratex.ui.components.common.stateError

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irajnajafi1988gmail.finratex.R
/**
 * A composable to display a full-screen error state with retry option.
 *
 * Shows an error message and a clickable retry row with an icon and label.
 *
 * @param onRetry lambda invoked when user taps retry
 */
@Composable
fun StateError(
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            // Main error message
            Text(
                text = "خطایی رخ داده است ",
                fontSize = 20.sp,
                color = Color.Red,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Suggestion text
            Text(
                text = "لطفا وضعیت اینترنت خود را بررسی کنید ",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Retry button row (clickable)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onRetry() },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Refresh icon
                Icon(
                    painter = painterResource(R.drawable.refresh),
                    contentDescription = "Retry",
                    modifier = Modifier.size(28.dp),
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Retry text
                Text(
                    text = "تلاش مجدد",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}
