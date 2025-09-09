package com.irajnajafi1988gmail.finratex.ui.components.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.irajnajafi1988gmail.finratex.R
import com.irajnajafi1988gmail.finratex.ui.components.common.state.DateUiState
import com.irajnajafi1988gmail.finratex.ui.components.common.stateError.StateError
import com.irajnajafi1988gmail.finratex.ui.viewModel.DateViewModel
import kotlinx.coroutines.delay

/**
 * Splash screen showing background image and loading animation.
 * Navigates to HomeScreen when data is loaded and timer finishes.
 *
 * @param navigateToHomeScreen Callback to navigate to HomeScreen
 * @param viewModel DateViewModel injected via Hilt
 */
@Composable
fun SplashScreen(
    navigateToHomeScreen: () -> Unit,
    viewModel: DateViewModel = hiltViewModel()
) {
    // Collect date state from ViewModel
    val dateState by viewModel.dateState.collectAsState()

    // Timer state to ensure splash shows for at least 2 seconds
    var isTimerFinished by remember { mutableStateOf(false) }

    // Launch a coroutine to wait for 2 seconds
    LaunchedEffect(true) {
        delay(2000)
        isTimerFinished = true
    }

    Box(modifier = Modifier) {
        // Background image
        Image(
            painter = painterResource(R.drawable.golden),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        when {
            // Show loading dots while data is loading or timer not finished
            dateState is DateUiState.Loading || !isTimerFinished -> {

                val infiniteTransition = rememberInfiniteTransition()
                val animatedValue by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 3f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1200, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    )
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 70.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Three dots animation
                    (0..2).forEach { index ->
                        val alpha = if (animatedValue.toInt() == index) 1f else 0.3f
                        Text(
                            "•",
                            fontSize = 40.sp,
                            color = Color.Green,
                            modifier = Modifier.alpha(alpha)
                        )
                    }
                }
            }

            // Show error state if loading fails
            dateState is DateUiState.Error -> {
                StateError(onRetry = { viewModel.refreshDate() })
            }

            // Navigate to HomeScreen when data is loaded and timer finished
            dateState is DateUiState.Success -> {
                LaunchedEffect(dateState, isTimerFinished) {
                    if (isTimerFinished) navigateToHomeScreen()
                }
            }

            // Show empty state if no data is available
            dateState is DateUiState.Empty -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "داده‌ای موجود نیست",
                        fontSize = 20.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    StateError(onRetry = { viewModel.refreshDate() })
                }
            }
        }
    }
}
