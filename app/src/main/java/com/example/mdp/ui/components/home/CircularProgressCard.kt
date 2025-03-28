package com.example.mdp.ui.components.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressCard(
    amountsConsumed: Float,
    dailyAmountGoal: Float,
    size: Dp,
    color: Color,
    type: String
) {
    val progress = (amountsConsumed / dailyAmountGoal).coerceIn(0f, 1f)
    val strokeWidth = with(LocalDensity.current) { 8.dp.toPx() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(size)
    ) {
        Canvas(modifier = Modifier.size(size)) {
            val diameter = this.size.minDimension
            val topLeftOffset = Offset(
                (this.size.width - diameter) / 2,
                (this.size.height - diameter) / 2
            )

            // Background track
            drawArc(
                color = Color(0xFF5E5E5E),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                size = Size(diameter, diameter),
                topLeft = topLeftOffset,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            // Foreground progress
            drawArc(
                color = color,
                startAngle = -90f, // Start at top
                sweepAngle = 360f * progress,
                useCenter = false,
                size = Size(diameter, diameter),
                topLeft = topLeftOffset,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(type)
            Text("${amountsConsumed.toInt()} g")
        }
    }
}
