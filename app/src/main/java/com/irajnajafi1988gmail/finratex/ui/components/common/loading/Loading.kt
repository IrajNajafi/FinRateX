package com.irajnajafi1988gmail.finratex.ui.components.common.loading

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

/**
 * A generic loading indicator with animated dots.
 *
 * @param modifier optional Modifier for styling
 * @param dotColor color of the loading dots
 * @param dotSize size of each dot in sp
 * @param dotCount number of dots to animate
 * @param animationDuration duration of one animation cycle
 * @param animationDelayStep delay between consecutive dot animations
 */
@Composable
fun StateLoading(
    modifier: Modifier = Modifier,
    dotColor: Color = Color.Gray,
    dotSize: Float = 60f,
    dotCount: Int = 3,
    animationDuration: Int = 300,
    animationDelayStep: Int = 150
) {
    // Center the loading dots in the parent container
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Create an infinite animation for the dots
        val infiniteTransition = rememberInfiniteTransition(label = "dots-loading-transition")

        // Horizontal row of dots
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            (0 until dotCount).forEach { index ->

                // Animate the alpha of each dot with a staggered delay
                val dotAlpha by infiniteTransition.animateFloat(
                    initialValue = 0.3f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = animationDuration,
                            delayMillis = index * animationDelayStep,
                            easing = LinearEasing
                        ),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "dot-alpha-$index"
                )

                // Draw the dot with animated alpha
                Text(
                    text = "•",
                    fontSize = dotSize.sp,
                    color = dotColor,
                    modifier = Modifier.alpha(dotAlpha)
                )
            }
        }
    }
}
